package com.erik.android.androidlean.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.Ohters.TestApplication;
import com.erik.android.androidlean.Ohters.TestSingleton;

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
