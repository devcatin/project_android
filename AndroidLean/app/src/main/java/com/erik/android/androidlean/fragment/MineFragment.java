package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.BaseActivity;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

import cc.shinichi.library.ImagePreview;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private boolean isPrepared;

    public MineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        isPrepared = true;
        lazyLoad();
        navigationBar(view);
        bindViews(view);
        return view;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        //填充各控件的数据
    }

    private void bindViews(View view) {
        Button btn_download = view.findViewById(R.id.btn_download);
        btn_download.setOnClickListener(this);
        Button btn_view_webview = view.findViewById(R.id.btn_view_webview);
        btn_view_webview.setOnClickListener(this);
        Button btn_view_image = view.findViewById(R.id.btn_view_image);
        btn_view_image.setOnClickListener(this);
        Button btn_draw_board = view.findViewById(R.id.btn_draw_board);
        btn_draw_board.setOnClickListener(this);
        Button btn_matrix_color = view.findViewById(R.id.btn_matrix_color);
        btn_matrix_color.setOnClickListener(this);
        Button btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        Button preforence = view.findViewById(R.id.btn_preforence);
        preforence.setOnClickListener(this);
        Button view_larger_image = view.findViewById(R.id.btn_view_larger_image);
        view_larger_image.setOnClickListener(this);
        Button change_volume = view.findViewById(R.id.btn_change_volume);
        change_volume.setOnClickListener(this);
        Button qrcode = view.findViewById(R.id.btn_qrcode);
        qrcode.setOnClickListener(this);
        Button location = view.findViewById(R.id.btn_location);
        location.setOnClickListener(this);
        Button wallpapper = view.findViewById(R.id.btn_wallpapper);
        wallpapper.setOnClickListener(this);
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

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                enterDownloadActivity();
                break;
            case R.id.btn_view_webview:
                enterWebviewActivity();
                break;
            case R.id.btn_view_image:
                enterImageActivity();
                break;
            case R.id.btn_draw_board:
                enterDrawBoardActivity();
                break;
            case R.id.btn_matrix_color:
                enterMatrixActivity();
                break;
            case R.id.btn_login:
                enterLoginActivity();
                break;
            case R.id.btn_preforence:
                enterPreforenceActivity();
                break;
            case R.id.btn_view_larger_image:
                //viewLargeImage();
                enterViewLargerImageActivity();
                break;
            case R.id.btn_change_volume:
                enterChangeVolume();
                break;
            case R.id.btn_qrcode:
                enterQRCode();
                break;
            case R.id.btn_location:
                enterLocation();
                break;
            case R.id.btn_wallpapper:
                enterWallPapper();
                break;
        }
    }

    private void enterDownloadActivity() {
        ARouter.getInstance().build(ConstConfig.DOWNLOAD_ACTIVITY)
                .withString("name", "文件下载")
                .navigation();
    }

    private void enterWebviewActivity() {
        ARouter.getInstance().build(ConstConfig.WEBVIEW_ACTIVITY)
                .withString("name", "加载网页")
                .navigation();
    }

    private void enterImageActivity() {
        ARouter.getInstance().build(ConstConfig.IMAGE_ACTIVITY)
                .withString("name", "图片浏览")
                .navigation();
    }

    private void enterDrawBoardActivity() {
        ARouter.getInstance().build(ConstConfig.DRAWBOARD_ACTIVITY)
                .withString("name", "我的画板")
                .navigation();
    }

    private void enterMatrixActivity() {
        ARouter.getInstance().build(ConstConfig.MATRIX_ACTIVITY)
                .withString("name", "图片过滤")
                .navigation();
    }

    private void enterLoginActivity() {
        ARouter.getInstance().build(ConstConfig.LOGIN_ACTIVITY)
                .withString("name", "登录")
                .navigation();
    }

    private void enterPreforenceActivity() {
        ARouter.getInstance().build(ConstConfig.PREFORENCE_ACTIVITY)
                .withString("name", "数据保存")
                .navigation();
    }

    private void enterViewLargerImageActivity() {
        ARouter.getInstance().build(ConstConfig.VIEW_LARGER_IMAGE_ACTIVITY)
                .withString("name", "查看大图")
                .navigation();
    }

    private void viewLargeImage() {
        String url = "https://v.iduov.com/insurance-person/d709c946-cee5-4311-9d51-226758610dab.png";
        BaseActivity activity = (BaseActivity)getActivity();
        ImagePreview
                .getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext(activity)

                // 设置从第几张开始看（索引从0开始）
                .setIndex(0)
                .setEnableClickClose(false)
                .setShowCloseButton(true)

                //=================================================================================================
                // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                // 1：第一步生成的imageInfo List
                // .setImageInfoList(imageInfoList)

                // 2：直接传url List
                //.setImageList(List<String> imageList)

                // 3：只有一张图片的情况，可以直接传入这张图片的url
                .setImage(url)
                //=================================================================================================

                // 开启预览
                .start();
    }

    private void enterChangeVolume() {
        ARouter.getInstance().build(ConstConfig.VOLUME_ACTIVITY)
                .withString("name", "调节音量")
                .navigation();
    }

    private void enterQRCode() {
        ARouter.getInstance().build(ConstConfig.QRCODE_ACTIVITY)
                .withString("name", "生成二维码")
                .navigation();
    }

    private void enterLocation() {
        ARouter.getInstance().build(ConstConfig.LOCATION_ACTIVITY)
                .withString("name", "地图定位")
                .navigation();
    }

    private void enterWallPapper() {
        ARouter.getInstance().build(ConstConfig.WALLPAPPER_ACTIVITY)
                .withString("name", "切换壁纸")
                .navigation();
    }

}
