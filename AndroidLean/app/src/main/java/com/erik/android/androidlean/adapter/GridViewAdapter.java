package com.erik.android.androidlean.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.activity.DetailActivity;
import com.erik.android.androidlean.bean.ClassBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.databinding.HomeGridViewItemBinding;
import com.erik.utilslibrary.ActivityManager;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private List<ClassBean> beans;
    private Context context;
    private LayoutInflater inflater;

    public GridViewAdapter(Context context, List<ClassBean> beans) {
        this.beans = beans;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ListViewHolder listViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_grid_view_item, parent, false);
            listViewHolder = new ListViewHolder(convertView);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ListViewHolder)convertView.getTag();
        }
        final ClassBean bean = beans.get(position);
        listViewHolder.cl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(ConstConfig.DETAIL_ACTIVITY)
                        .withString("name", bean.getTitle())
                        .navigation();
            }
        });
        listViewHolder.bind(bean);
        return convertView;
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private HomeGridViewItemBinding itemBinding;
        public ConstraintLayout cl_layout;

        private ListViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
            cl_layout = itemView.findViewById(R.id.cl_layout);
        }

        private void bind(@NonNull ClassBean bean) {
            itemBinding.setBean(bean);
        }

    }

}
