package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.fragment.MyDialog;
import com.erik.android.androidlean.fragment.MyDialogFragment;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.DIALOG_ACTIVITY)
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

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

            }
        });
    }

    private void bindViews() {
        Button normalDialog = findViewById(R.id.btn_normal_dialog);
        normalDialog.setOnClickListener(this);
        Button fragmentDialog = findViewById(R.id.btn_fragment_dialog);
        fragmentDialog.setOnClickListener(this);
    }

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal_dialog:
                showNormalDialog();
                break;
            case R.id.btn_fragment_dialog:
                showDialogFragment();
                break;
        }
    }

    private void showNormalDialog() {
        MyDialog mNormalDialog = new MyDialog(DialogActivity.this);
        mNormalDialog.show();
    }

    private void showDialogFragment() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
    }

}
