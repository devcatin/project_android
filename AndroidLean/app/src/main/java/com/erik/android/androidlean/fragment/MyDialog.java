package com.erik.android.androidlean.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.erik.android.androidlean.R;

public class MyDialog extends Dialog {

    private String TAG = "xp.chen-Dialog";

    public MyDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_normal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: MyDialog->onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: MyDialog->onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: MyDialog->onStop()");
    }

    @Override
    public void cancel() {
        super.cancel();
        Log.i(TAG, "cancel: MyDialog->cancel()");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.i(TAG, "dismiss: MyDialog->dismiss()");
    }

}
