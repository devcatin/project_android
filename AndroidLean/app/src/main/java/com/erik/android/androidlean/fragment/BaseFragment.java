package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.erik.android.androidlean.ohter.TestApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class BaseFragment extends Fragment {

    private Activity activity;
    //分页请求页数初始值
    public int pageIndex = 1;
    //是否是上拉
    public boolean isup = false;
    //是否是上拉和下拉
    public boolean isDoubleRefresh = false;
    public SmartRefreshLayout refreshLayout;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x123) {
                messageEventPostThread(msg);
                if (refreshLayout != null) {
                    if (isup) {
                        //上拉加载更多成功
                        refreshLayout.finishLoadMore(500);
                    } else {
                        //下拉刷新成功
                        refreshLayout.finishRefresh(500);
                    }
                }
            } else if (msg.what == 0x456) {
                if (refreshLayout != null) {
                    if (isup) {
                        //上拉加载更多失败
                        refreshLayout.finishLoadMore(false);
                    } else {
                        //下拉刷新失败
                        refreshLayout.finishRefresh(false);
                    }
                }
            }
            return false;
        }
    });

    public Context getContext() {
        if (activity == null) {
            return TestApplication.getInstance();
        }
        return activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //初始化刷新器
        if (refreshLayout != null) {
            refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    isup = false;
                    requestData();
                }
            });
            if (isDoubleRefresh) {
                refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
                refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RefreshLayout refreshlayout) {
                        pageIndex++;
                        isup = true;
                        requestData();
                    }
                });
            }
        }
        requestData();
    }

    //回调处理
    public void messageEventPostThread(Message msg) {

    }

    //网络请求
    public void requestData() {

    }

}
