package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.tool.GlideImageLoader;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;
import com.erik.utilslibrary.UtilsTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Context context;
    private Banner banner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        navigationBar(view);
        loadBanner(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    private void navigationBar(View view) {
        final NavigationBar navigationBar = view.findViewById(R.id.nav_bar);
        navigationBar.setShowBackBtn(false);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {
                registerForContextMenu(navigationBar.getBtn_right());
            }
        });
    }

    private void loadBanner(View view) {
        banner = view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        List<String> images = new ArrayList<>();
        images.add("http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg");
        images.add("http://pic18.nipic.com/20111214/6834314_092609528357_2.jpg");
        images.add("http://pic16.nipic.com/20111006/6239936_092702973000_2.jpg");
        images.add("http://hbimg.b0.upaiyun.com/a8f1b16790e92888dc2033125da59cea80cb60b519536-dbgwl3_fw658");
        images.add("http://pic.rmb.bdstatic.com/586a836a5a8bf661114166e2df414074.jpeg");
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.Accordion);
        List<String> titles = new ArrayList<>();
        titles.add("123");
        titles.add("321");
        titles.add("231");
        titles.add("213");
        titles.add("123");
        banner.setBannerTitles(titles);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    @Override
    // 重写上下文菜单的创建方法
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(context);
        inflator.inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // 上下文菜单被点击是触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                new Thread() {
                    public void run() {

                    }
                }.start();
                break;
            case R.id.two:
                new Thread() {
                    public void run() {

                    }
                }.start();
                break;
            case R.id.three:

                break;
        }
        return true;
    }

}
