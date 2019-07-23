package com.erik.android.androidlean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.erik.android.androidlean.Bean.NewBean;
import com.erik.android.androidlean.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<NewBean> beans;
    private Context context;

    public ListAdapter(List<NewBean> beans, Context context) {
        this.beans = beans;
        this.context = context;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_item_title = convertView.findViewById(R.id.txt_item_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.txt_item_title.setText(beans.get(position).getNew_title());
        return convertView;
    }

    private class ViewHolder {
        TextView txt_item_title;
    }

}
