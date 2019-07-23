package com.erik.android.androidlean.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erik.android.androidlean.R;

public class TestFregment extends Fragment {

    final static String TAG = "TestFregment";

    private String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_content, container, false);
        Bundle mBundle = getArguments();
        String value = mBundle.getString("content");
        Log.i(TAG, value);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(value);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void getData(CallBack callBack) {
        Bundle mBundle = getArguments();
        String value = mBundle.getString("key");
        callBack.getResult(value);
    }

    public interface CallBack {
        public void getResult(String result);
    }

}
