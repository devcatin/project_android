package com.erik.android.androidlean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erik.android.androidlean.R;

public class DetailFregment extends Fragment {

    public DetailFregment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_content, container, false);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(getArguments().getString("content"));
        return view;
    }
}
