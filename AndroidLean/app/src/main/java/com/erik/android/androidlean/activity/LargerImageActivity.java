package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.BigImageView;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;

import java.io.IOException;
import java.io.InputStream;


@Route(path = ConstConfig.VIEW_LARGER_IMAGE_ACTIVITY)
public class LargerImageActivity extends BaseActivity {

    private BigImageView imageView;
    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BigImageViewer.initialize(GlideImageLoader.with(getApplicationContext()));
        setContentView(R.layout.activity_larger_image);
        navigationBar();
        bindViews();
    }

    private void bindViews() {
        imageView = findViewById(R.id.imageView);
        //String url = "https://v.iduov.com/insurance-person/d709c946-cee5-4311-9d51-226758610dab.png";
//        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568629148890&di=fc1bcb68dd7db9e365515e1cf203bbec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012ec4574584276ac72525aee9f5cd.jpg";
//        imageView.configWithImageUrl(url);
        String url = "big.png";
        imageView.configWithLocalImageUrl(url);
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
                Toast.makeText(LargerImageActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

}
