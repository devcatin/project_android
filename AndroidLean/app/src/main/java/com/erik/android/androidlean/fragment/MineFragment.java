package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.DownloadActivity;
import com.erik.android.androidlean.activity.ImageActivity;
import com.erik.android.androidlean.activity.WebViewActivity;
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
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                intent.putExtra("name", "文件下载");
                startActivity(intent);
                break;
            case R.id.btn_view_webview:
                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                intent1.putExtra("name", "网页加载");
                startActivity(intent1);
                break;
            case R.id.btn_view_image:
                Intent intent2 = new Intent(getActivity(), ImageActivity.class);
                intent2.putExtra("name","图片预览");
                startActivity(intent2);
                break;
        }
    }

}
