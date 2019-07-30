package com.erik.android.androidlean.ohter;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.erik.android.androidlean.activity.DaoMaster;
import com.erik.android.androidlean.activity.DaoSession;

public class TestApplication extends Application {

    private String state;
    private DaoSession daoSession;
    private static TestApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "data.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
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
