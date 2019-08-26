package com.erik.android.androidlean.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.LiveBean;
import com.erik.android.androidlean.databinding.HomeLiveItemBinding;
import com.erik.android.androidlean.databinding.ListItemBinding;

public class LiveHolder extends RecyclerView.ViewHolder {

    public HomeLiveItemBinding liveItemBinding;

    public LiveHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        liveItemBinding = DataBindingUtil.bind(itemView);
    }

    public void bind(@NonNull LiveBean liveBean) {
        liveItemBinding.setLive(liveBean);
    }

}
