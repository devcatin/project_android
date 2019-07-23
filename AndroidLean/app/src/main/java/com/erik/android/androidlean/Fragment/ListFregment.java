package com.erik.android.androidlean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.erik.android.androidlean.Adapter.ListAdapter;
import com.erik.android.androidlean.Bean.NewBean;
import com.erik.android.androidlean.R;

import java.util.ArrayList;

public class ListFregment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentManager manager;
    private ArrayList<NewBean> beans;
    private ListView list_new;

    public ListFregment(FragmentManager manager, ArrayList<NewBean> beans) {
        this.manager = manager;
        this.beans = beans;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newlist, container, false);
        list_new = view.findViewById(R.id.list_news);
        ListAdapter adapter = new ListAdapter(beans, getActivity());
        list_new.setAdapter(adapter);
        list_new.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction transaction = manager.beginTransaction();
    }

}
