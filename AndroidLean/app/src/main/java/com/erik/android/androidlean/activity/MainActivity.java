package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.erik.android.androidlean.adapter.TestFragmentPagerAdapter;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.bean.Student;
import com.erik.android.androidlean.bean.UserModel;
import com.erik.android.androidlean.fragment.SplashFragment;
import com.erik.android.androidlean.ohter.TestService;
import com.erik.android.androidlean.ohter.WeatherService;
import com.erik.utilslibrary.StatusBarUtil;

import java.lang.ref.WeakReference;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final String TAG = "TestService";

    private static final int NOTIFY_ID = 123;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_home;
    private RadioButton rb_hot;
    private RadioButton rb_buy;
    private RadioButton rb_mine;

    private ViewPager viewPager;
    private TestFragmentPagerAdapter adapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private ViewStub viewStub;
    private SplashFragment splashFragment;

    private Handler mHandler = new Handler();

    static class DelayRunnable implements Runnable{
        private WeakReference<Context> contextRef;
        private WeakReference<SplashFragment> fragmentRef;

        public DelayRunnable(Context context, SplashFragment f) {
            contextRef = new WeakReference<>(context);
            fragmentRef = new WeakReference<>(f);
        }

        @Override
        public void run() {
            if(contextRef!=null){
                SplashFragment splashFragment = fragmentRef.get();
                if(splashFragment==null){
                    return;
                }
                FragmentActivity activity = (FragmentActivity) contextRef.get();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.remove(splashFragment);
                transaction.commit();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashFragment = new SplashFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, splashFragment);
        transaction.commit();

        viewStub = findViewById(R.id.content_viewstub);

        //1.判断当窗体加载完毕的时候,就把我们真正的布局加载进来
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                // 开启延迟加载
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //将viewstub加载进来
                        viewStub.inflate();
                        initMain();
                        StatusBarUtil.cancelFullScreen(MainActivity.this);
                    }
                } );
            }
        });

        //2.判断当窗体加载完毕的时候执行,延迟一段时间是为了模拟做动画的耗时。
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                // 开启延迟加载,也可以不用延迟可以立马执行（我这里延迟是为了实现fragment里面的动画效果的耗时）
                mHandler.postDelayed(new DelayRunnable(MainActivity.this, splashFragment) ,2000);
            }
        });

        //3.同时进行异步加载数据
        SparseArray<String> map = new SparseArray<>();
        map.put(1, "Hello");

        final UserModel viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return null;
            }
        }).get(UserModel.class);
        viewModel.getStudent().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                //update ui.
            }
        });
    }

    private void initMain() {
        adapter = new TestFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_home.setChecked(true);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        Button button = new Button(this);
        RelativeLayout rly = new RelativeLayout(this);
        rly.addView(button, layoutParams);
    }

    private void test() {
        LayoutInflater inflater = LayoutInflater.from(this);
    }

    public static void addShortcut(Activity cx, String name) {
        // TODO: 2017/6/25  创建快捷方式的intent广播
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // TODO: 2017/6/25 添加快捷名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        //  快捷图标是允许重复
        shortcut.putExtra("duplicate", false);
        // 快捷图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        // TODO: 2017/6/25 我们下次启动要用的Intent信息
        Intent carryIntent = new Intent(Intent.ACTION_MAIN);
        carryIntent.putExtra("name", name);
        carryIntent.setClassName(cx.getPackageName(),cx.getClass().getName());
        carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //添加携带的Intent
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
        // TODO: 2017/6/25  发送广播
        cx.sendBroadcast(shortcut);
    }

    private void bindViews() {
        rg_tab_bar = findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);

        rb_home = findViewById(R.id.rb_home);
        rb_hot = findViewById(R.id.rb_hot);
        rb_buy = findViewById(R.id.rb_buy);
        rb_mine = findViewById(R.id.rb_mine);

        viewPager = findViewById(R.id.vpager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

        localNotification();
    }

    //本地通知
    private void localNotification() {
        // 本地通知
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String title = "晴转多云 22℃" ;
        String content = "上海市浦东新区" ;

        //1.实例化一个通知，指定图标、概要、时间
        //2.指定通知的标题、内容和intent
        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.weather)
                .setContentText(content)
                .setWhen(5000)
                .setContentIntent(pi)
                .build();
        //3.指定声音
        notification.defaults = Notification.DEFAULT_SOUND;
        //4.发送通知
        nm.notify(1, notification);
    }

    //位图缓存
    public void lruCache() {
        int memClass = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 8;
        LruCache cache = new LruCache<String, Bitmap>( cacheSize );
    }

    public static Bitmap decodeBitmapWithGiveSizeFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int widht = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || widht > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = widht / 2;
            while ((halfHeight / inSampleSize) > reqHeight &&(halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(MainActivity.this, event.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_hot:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_buy:
                viewPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_mine:
                viewPager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //0.表示什么都没做 1.正在滑动 2.滑动完成
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_home.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_hot.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_buy.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_mine.setChecked(true);
                    break;

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void viewSystemBrowser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    //获取手机联系人
    private void getContacts() {
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i(TAG, "姓名:" + cName + " 电话:" + cNum);
        }
        cursor.close();
    }

    //打电话
    public void makePhoneCall() {
        Uri uri = Uri.parse("tel:10086");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    //发短信
    public void sendMsg() {
        Uri uri = Uri.parse("smsto:10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "Hello");
        startActivity(intent);
    }

    //发彩信(带附件)
    public void sendAttachMsg() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra("sms_body", "Hello");
        Uri uri = Uri.parse("content://media/external/images/media/23");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/png");
        startActivity(intent);
    }

    //打开浏览器
    public void openBrowser() {
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    //发送电子邮件
    public void sendEmail() {
        Uri uri = Uri.parse("mailto:someone@domain.com");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(intent);

        // 给someone@domain.com发邮件发送内容为“Hello”的邮件
        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_EMAIL, "someone@domain.com");
        intent1.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent1.putExtra(Intent.EXTRA_TEXT, "Hello");
        intent1.setType("text/plain");
        startActivity(intent1);

        // 给多人发邮件
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        String[] tos = {"1@abc.com", "2@abc.com"}; // 收件人
        String[] ccs = {"3@abc.com", "4@abc.com"}; // 抄送
        String[] bccs = {"5@abc.com", "6@abc.com"}; // 密送
        intent2.putExtra(Intent.EXTRA_EMAIL, tos);
        intent2.putExtra(Intent.EXTRA_CC, ccs);
        intent2.putExtra(Intent.EXTRA_BCC, bccs);
        intent2.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent2.putExtra(Intent.EXTRA_TEXT, "Hello");
        intent2.setType("message/rfc822");
        startActivity(intent2);
    }

    //显示地图
    public void showMap() {
        Uri uri = Uri.parse("geo:39.9,116.3");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        // 路径规划：从北京某地（北纬39.9，东经116.3）到上海某地（北纬31.2，东经121.4）
        Uri uri1 = Uri.parse("http://maps.google.com/maps?f=d&saddr=39.9 116.3&daddr=31.2 121.4");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
        startActivity(intent1);
    }

    //多媒体播放
    public void palyAudio() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file:///sdcard/foo.mp3");
        intent.setDataAndType(uri, "audio/mp3");
        startActivity(intent);
    }

    //读取sdk中的文件(音频) 播放第一首
    public void readSDKFile() {
        Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    //打开摄像头拍照
    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
        // 取出照片数据
        Bundle extras = intent.getExtras();
        Bitmap bitmap = (Bitmap) extras.get("data");
    }

    //进入联系人
    public void contact() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Contacts.People.CONTENT_URI);
        startActivity(intent);
    }

    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }

}
