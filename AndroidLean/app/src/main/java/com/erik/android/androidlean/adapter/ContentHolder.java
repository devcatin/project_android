package com.erik.android.androidlean.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erik.android.androidlean.R;

public class ContentHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public View topLine;
    public View bottomLine;
    public ConstraintLayout clItemView;

    public ContentHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        titleView = itemView.findViewById(R.id.tv_title);
        topLine = itemView.findViewById(R.id.v_top_line);
        bottomLine = itemView.findViewById(R.id.v_bottom_line);
        clItemView = itemView.findViewById(R.id.cl_item_view);
    }

}
