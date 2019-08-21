package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.ohter.WallPaperService;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

import java.io.IOException;

@Route(path = ConstConfig.WALLPAPPER_ACTIVITY)
public class ChangeWallPagerActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_on;
    private Button btn_off;
    private Button btn_clean;
    private AlarmManager aManager;
    private PendingIntent pi;

    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_wall_pager);

        //①获得AlarmManager对象:
        aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //②指定要启动的Service,并指明动作是Servce:
        Intent intent = new Intent(ChangeWallPagerActivity.this, WallPaperService.class);
        pi = PendingIntent.getService(ChangeWallPagerActivity.this, 0, intent, 0);
        navigationBar();
        bindViews();
    }

    private void navigationBar() {
        final NavigationBar navigationBar = findViewById(R.id.nav_bar);
        navigationBar.setTitleTextStr(name);
        navigationBar.setShowBackBtn(true);
        navigationBar.setBtnOnClickListener(new NavigationBar.ButtonOnClickListener() {
            @Override
            public void onBackClick() {
                Activity activity = ActivityManager.getActivity().get();
                activity.finish();
            }
            @Override
            public void onRightClick() {
                Toast.makeText(ChangeWallPagerActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViews() {
        btn_on = findViewById(R.id.btn_on);
        btn_off = findViewById(R.id.btn_off);
        btn_clean = findViewById(R.id.btn_clean);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_on:
                aManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 3000, pi);
                btn_on.setEnabled(false);
                btn_off.setEnabled(true);
                Toast.makeText(ChangeWallPagerActivity.this, "自动更换壁纸设置成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_off:
                btn_on.setEnabled(true);
                btn_off.setEnabled(false);
                aManager.cancel(pi);
                break;
            case R.id.btn_clean:
                try {
                    WallpaperManager.getInstance(getApplicationContext()).clear();
                    Toast.makeText(ChangeWallPagerActivity.this, "清除壁纸成功~", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
