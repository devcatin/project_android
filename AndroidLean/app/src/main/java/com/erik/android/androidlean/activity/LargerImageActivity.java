package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;

@Route(path = ConstConfig.VIEW_LARGER_IMAGE_ACTIVITY)
public class LargerImageActivity extends AppCompatActivity {

    private BigImageView mBigImage;
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
        mBigImage = findViewById(R.id.mBigImage);
        mBigImage.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER_CROP);
        String url = "https://v.iduov.com/insurance-person/d709c946-cee5-4311-9d51-226758610dab.png";
        mBigImage.showImage(Uri.parse(url));
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

}
