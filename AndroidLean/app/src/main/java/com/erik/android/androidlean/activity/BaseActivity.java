package com.erik.android.androidlean.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erik.utilslibrary.ActivityManager;
import com.github.zackratos.ultimatebar.UltimateBar;

public class BaseActivity extends AppCompatActivity {

    private static long lastClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getActivity().add(this);
        //ARouter 注入
        ARouter.getInstance().inject(this);

        UltimateBar.Companion.with(this)
                .statusDark(true)                  // 状态栏灰色模式(Android 6.0+)，默认 flase
                .statusDrawable2(null)         // Android 6.0 以下状态栏灰色模式时状态栏颜色
                .applyNavigation(true)              // 应用到导航栏，默认 flase
                .navigationDark(false)              // 导航栏灰色模式(Android 8.0+)，默认 false
                .navigationDrawable2(null)     // Android 8.0 以下导航栏灰色模式时导航栏颜色
                .create()
                .immersionBar();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getActivity().remove(this);
        super.onDestroy();
    }

    public boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
