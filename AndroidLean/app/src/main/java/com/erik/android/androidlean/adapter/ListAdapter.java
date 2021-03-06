package com.erik.android.androidlean.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.databinding.ListItemBinding;
import com.erik.android.androidlean.view.CustomRoundAngleImageView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<UserBean> beans;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<UserBean> beans) {
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
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            listViewHolder = new ListViewHolder(convertView);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ListViewHolder)convertView.getTag();
        }
        UserBean userBean = beans.get(position);
        listViewHolder.bind(userBean);
        return convertView;
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(CustomRoundAngleImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private ListItemBinding itemBinding;

        private ListViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        private void bind(@NonNull UserBean userBean) {
            itemBinding.setUser(userBean);
        }
    }

}
