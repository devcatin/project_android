package com.erik.android.androidlean;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.erik.utilslibrary.UtilsTools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("key", "Hello world!");
        int sum = UtilsTools.add(100, 120);
    }
}
