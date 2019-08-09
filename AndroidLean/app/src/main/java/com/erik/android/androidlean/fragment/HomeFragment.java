package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.BannerBean;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.network.BaseResponse;
import com.erik.android.androidlean.network.NetRequest;
import com.erik.android.androidlean.network.RequestMethod;
import com.erik.android.androidlean.tool.GlideImageLoader;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.qrcodelibrary.ZXingUtils;
import com.erik.utilslibrary.ActivityManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class HomeFragment extends Fragment {

    private Context context;
    private Banner banner;
    private List<String> lists = new ArrayList<>();

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x123) {
                banner.setImages(lists);
                banner.start();
            }
            return false;
        }
    });

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
        bindViews(view);
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

    private void requestData() {
        NetRequest netRequest = NetRequest.getInstance(context);
        netRequest.get(RequestMethod.GET_BANNER_LIST, new NetRequest.RequestCallBack() {
            @Override
            public void success(Response response) throws IOException {
                int code = response.code();
                if (code == 200) {
                    String result = response.body().string();
                    Log.i("tag", result);
                    BaseResponse baseResponse = JSON.parseObject(result, BaseResponse.class);
                    String data = baseResponse.getData();
                    List<BannerBean> list = JSON.parseArray(data, BannerBean.class);
                    for (int index = 0; index < list.size(); index++) {
                        BannerBean bannerBean = list.get(index);
                        lists.add(bannerBean.getImgUrl());
                    }
                    Message message = new Message();
                    message.what = 0x123;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void failure(IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadBanner(View view) {
        banner = view.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Accordion);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    private void bindViews(View view) {
        //ImageView imageView = view.findViewById(R.id.iv_qrcode);
        //Bitmap bitmap = ZXingUtils.createQRImage("https://www.baidu.com", 300, 300);
        //imageView.setImageBitmap(bitmap);
        requestData();
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
