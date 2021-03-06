package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.IMAGE_ACTIVITY)
public class ImageActivity extends BaseActivity {

    private ImageView img_show;
    private AnimationDrawable animationDrawable;

    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        navigationBar();
        bindViews();
    }

    private void bindViews() {
        img_show = findViewById(R.id.img_show);
        animationDrawable = (AnimationDrawable) img_show.getDrawable();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        }, 300);
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
                Toast.makeText(ImageActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

}
