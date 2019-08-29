package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.LOCATION_ACTIVITY)
public class LocationActivity extends BaseActivity {

    @Autowired(name = "name")
    public String name;

    private LocationManager locationManager;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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
                Toast.makeText(LocationActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViews() {
        tv_show = findViewById(R.id.tv_show);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (!isGpsAble(locationManager)) {
            Toast.makeText(LocationActivity.this, "请打开GPS~",Toast.LENGTH_SHORT).show();
            openGPS2();
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateShow(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

    //定义一个更新显示的方法
    private void updateShow(Location location) {
        if (location != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("当前的位置信息：\n");
            sb.append("经度：" + location.getLongitude() + "\n");
            sb.append("纬度：" + location.getLatitude() + "\n");
            sb.append("高度：" + location.getAltitude() + "\n");
            sb.append("速度：" + location.getSpeed() + "\n");
            sb.append("方向：" + location.getBearing() + "\n");
            sb.append("定位精度：" + location.getAccuracy() + "\n");
            tv_show.setText(sb.toString());
        } else tv_show.setText("");
    }

    private boolean isGpsAble(LocationManager lm) {
        boolean isAble = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isAble) {
            return true;
        }
        return false;
    }

    private void openGPS2() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 0);
    }

}
