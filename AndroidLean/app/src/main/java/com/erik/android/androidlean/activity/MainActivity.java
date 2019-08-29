package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.erik.android.androidlean.adapter.TestFragmentPagerAdapter;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final String TAG = "TestService";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
