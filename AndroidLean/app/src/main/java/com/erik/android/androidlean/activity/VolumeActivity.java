package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.VOLUME_ACTIVITY)
public class VolumeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_show;

    private Button start;
    private Button stop;
    private Button higher;
    private Button lower;
    private Button quite;

    @Autowired(name = "name")
    public String name;

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        navigationBar();
        bindViews();

        mediaPlayer = MediaPlayer.create(VolumeActivity.this, R.raw.audio);
        audioManager = (AudioManager)getSystemService(Service.AUDIO_SERVICE);
        mediaPlayer.setLooping(true);

        RelativeLayout rly = findViewById(R.id.rly_layout);
        Button btnLoad = new Button(this);
        btnLoad.setText("切换壁纸");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        rly.addView(btnLoad, params);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
    }

    //Android 4.2之前
    public static int[] getScreenHW(Context context) {
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        int[] HW = new int[] {width, height};
        return HW;
    }

    //Android 4.2之后
    public static int[] getScreenHW1(Context context) {
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int[] HW = new int[] {width, height};
        return HW;
    }

    public static int getScreenW(Context context) {
        return getScreenHW1(context)[0];
    }

    public static int getScreenH(Context context) {
        return getScreenHW1(context)[1];
    }

    private void navigationBar() {
        final NavigationBar navigationBar = findViewById(R.id.nav_bar);
        navigationBar.setTitleTextStr(name);
        navigationBar.setShowBackBtn(true);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {
                Toast.makeText(VolumeActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViews() {
        start = findViewById(R.id.btn_start);
        start.setOnClickListener(this);
        stop = findViewById(R.id.btn_stop);
        stop.setOnClickListener(this);
        higher = findViewById(R.id.btn_higher);
        higher.setOnClickListener(this);
        lower = findViewById(R.id.btn_lower);
        lower.setOnClickListener(this);
        quite = findViewById(R.id.btn_quite);
        quite.setOnClickListener(this);
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

    @Override
    public void onClick(View view) {
        int tag = view.getId();
        switch (tag) {
            case R.id.btn_start:
                stop.setEnabled(true);
                mediaPlayer.start();
                start.setEnabled(false);
                break;
            case R.id.btn_stop:
                start.setEnabled(true);
                mediaPlayer.pause();
                stop.setEnabled(false);
                break;
            case R.id.btn_higher:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                break;
            case R.id.btn_lower:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                break;
            case R.id.btn_quite:
                flag *= -1;
                if (flag == -1) {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                    //audioManager.adjustSuggestedStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                    quite.setText("取消静音");
                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    //audioManager.adjustSuggestedStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                    audioManager.setMicrophoneMute(false);
                    quite.setText("静音");
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

}
