package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

public class MineFragment extends Fragment implements View.OnClickListener {

    public MineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        navigationBar(view);
        bindViews(view);
        return view;
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

}
