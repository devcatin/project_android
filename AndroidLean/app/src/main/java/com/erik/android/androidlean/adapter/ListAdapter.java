package com.erik.android.androidlean.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.databinding.ListItemBinding;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<NewBean> beans;
    private Context context;
    private LayoutInflater inflater;
    private int resId;

    public ListAdapter(List<NewBean> beans, Context context, int resId) {
        this.beans = beans;
        this.context = context;
        this.resId = resId;
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
        ListItemBinding itemBinding;
        if (convertView == null) {
            itemBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
            convertView = itemBinding.getRoot();
        } else {
            itemBinding = DataBindingUtil.getBinding(convertView);
        }
        NewBean newBean = beans.get(position);
        itemBinding.setVariable(resId, newBean);
        Glide.with(context).load(newBean.getNew_icon()).into(itemBinding.ivHead);
        return convertView;
    }

}
