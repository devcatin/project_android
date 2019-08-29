package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.shizhefei.view.largeimage.LargeImageView;


@Route(path = ConstConfig.VIEW_LARGER_IMAGE_ACTIVITY)
public class LargerImageActivity extends BaseActivity {

    private LargeImageView imageView;
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
        String url = "https://v.iduov.com/insurance-person/d709c946-cee5-4311-9d51-226758610dab.png";
        /*imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
        imageView.setMinScale(1.5f);
        imageView.setMaxScale(1.5f);
        Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                ImageSource imageSource = ImageSource.uri(Uri.fromFile(resource));
                //imageView.setImage(imageSource, new ImageViewState(1.5f, new PointF(0, 0), 0));
            }
        });*/
        Glide.with(this).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageView.setImage(resource);
            }
        });
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
