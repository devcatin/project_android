package com.erik.android.androidlean.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.DownloadActivity;
import com.erik.android.androidlean.activity.WebViewActivity;

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
        Button btn_download = view.findViewById(R.id.btn_download);
        btn_download.setOnClickListener(this);
        Button btn_view_webview = view.findViewById(R.id.btn_view_webview);
        btn_view_webview.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                intent.putExtra("name", "download");
                startActivity(intent);
                break;
            case R.id.btn_view_webview:
                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                intent1.putExtra("name", "webview");
                startActivity(intent1);
                break;
        }
    }

}
