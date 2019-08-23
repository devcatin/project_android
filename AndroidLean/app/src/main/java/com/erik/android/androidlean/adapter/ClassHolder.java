package com.erik.android.androidlean.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.tool.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

public class ClassHolder extends RecyclerView.ViewHolder {

    public GridView gridView;

    public ClassHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        gridView = itemView.findViewById(R.id.gv_grid_view);
    }

}
