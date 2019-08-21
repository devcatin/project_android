package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.ProgressIndicator;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.shizhefei.view.largeimage.LargeImageView;

import java.io.File;

import cc.shinichi.library.ImagePreview;
import okhttp3.internal.Util;


@Route(path = ConstConfig.VIEW_LARGER_IMAGE_ACTIVITY)
public class LargerImageActivity extends AppCompatActivity {

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

}
