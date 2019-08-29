package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.qrcodelibrary.ZXingUtils;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.QRCODE_ACTIVITY)
public class QRCodeActivity extends BaseActivity {

    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
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
                Toast.makeText(QRCodeActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViews() {
        ImageView imageView = findViewById(R.id.iv_qrcode);
        Bitmap bitmap = ZXingUtils.createQRImage("https://www.baidu.com", 300, 300);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

}
