package com.erik.android.androidlean.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erik.android.androidlean.R;

public class FooterHolder extends RecyclerView.ViewHolder {

    public TextView titleView;

    public FooterHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        titleView = itemView.findViewById(R.id.tv_title);
    }

}
