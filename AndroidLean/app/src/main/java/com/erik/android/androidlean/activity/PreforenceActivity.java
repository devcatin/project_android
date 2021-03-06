package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.tool.SharedHelper;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

import java.util.Map;

@Route(path = ConstConfig.PREFORENCE_ACTIVITY)
public class PreforenceActivity extends BaseActivity implements View.OnClickListener {

    @Autowired(name = "name")
    public String name;

    private EditText editname;
    private EditText editdetail;
    private Button btn_save;
    private Button btnclean;
    private Button btnread;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preforence);

        mContext = getApplicationContext();
        navigationBar();
        bindViews();
    }

    private void bindViews() {
        editdetail = findViewById(R.id.editdetail);
        editname = findViewById(R.id.editname);
        btnclean = findViewById(R.id.btnclean);
        btn_save = findViewById(R.id.btnsave);
        btnread = findViewById(R.id.btnread);

        btnclean.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btnread.setOnClickListener(this);
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

    @Override
    public void onMessageEvent(MessageEvent event) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnclean:
                editdetail.setText("");
                editname.setText("");
                break;
            case R.id.btnsave:
                SharedHelper sharedHelper = new SharedHelper(mContext);
                String filename = editname.getText().toString();
                String filedetail = editdetail.getText().toString();
                try {
                    sharedHelper.saveData(filename, filedetail);
                    Toast.makeText(mContext, "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnread:
                SharedHelper sharedHelper1 = new SharedHelper(mContext);
                Map<String, String> map = sharedHelper1.readData();
                String value = "suername:" + map.get("username") + " passwd:" + map.get("passwd");
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
