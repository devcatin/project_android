package com.erik.android.androidlean.ohter;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erik.android.androidlean.activity.DaoMaster;
import com.erik.android.androidlean.activity.DaoSession;

import java.util.HashMap;

public class TestApplication extends Application {

    private String state;
    private DaoSession daoSession;
    private static TestApplication instance;
    //公共信息映射类
    public HashMap<String, String> infoMap = new HashMap<String, String>();

    //ARouter调试开关
    private boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //initGreenDao();
        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        //ARouter注册初始化
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //释放回收
        ARouter.getInstance().destroy();
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
