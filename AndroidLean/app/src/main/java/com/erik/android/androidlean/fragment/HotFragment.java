package com.erik.android.androidlean.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.erik.android.androidlean.activity.DetailActivity;
import com.erik.android.androidlean.adapter.ListAdapter;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

import java.util.ArrayList;

public class HotFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentManager manager;
    private ArrayList<NewBean> beans;
    private Context context;
    private ListView list_new;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    private ArrayList<NewBean> createData() {
        ArrayList<NewBean>list = new ArrayList<NewBean>();
        for (int index = 0; index < 20; index++) {
            String title = "测试" + index;
            String content = "内容" + index;
            NewBean bean = new NewBean(title, content);
            list.add(bean);
        }
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newlist, container, false);
        navigationBar(view);
        bindViews(view);
        return view;
    }

    private void bindViews(View view) {
        list_new = view.findViewById(R.id.list_news);
        this.beans = this.createData();
        ListAdapter adapter = new ListAdapter(beans, getActivity());
        list_new.setAdapter(adapter);
        list_new.setOnItemClickListener(this);
    }

    private void navigationBar(View view) {
        final NavigationBar navigationBar = view.findViewById(R.id.nav_bar);
        navigationBar.setShowBackBtn(false);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewBean bean = beans.get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("content",bean.getNew_content());
        intent.putExtra("title", bean.getNew_title());
        startActivity(intent);
    }
}
