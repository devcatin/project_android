package com.erik.android.androidlean.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.ohter.TestApplication;
import com.erik.android.androidlean.ohter.TestSingleton;

public class OneActivity extends AppCompatActivity {

    static String TAG = "OneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        TestApplication application = TestApplication.getInstance();
        String state = application.getState();
        Toast.makeText(OneActivity.this, state, Toast.LENGTH_SHORT).show();

        TestSingleton.getInstance().put("key", "123");
    }
}
