package com.erik.android.androidlean.Ohters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "com.example.broadcasttest.MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT.equals(intent.getAction()))
            Toast.makeText(context, "收到告白啦~", Toast.LENGTH_SHORT).show();
    }

}
