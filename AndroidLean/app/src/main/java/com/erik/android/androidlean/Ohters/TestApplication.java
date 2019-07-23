package com.erik.android.androidlean.Ohters;

import android.app.Application;

public class TestApplication extends Application {

    private String state;

    private static TestApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static TestApplication getInstance() {
        return instance;
    }
}
