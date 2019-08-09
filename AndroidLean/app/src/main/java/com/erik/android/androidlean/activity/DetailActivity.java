package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.erik.android.androidlean.BR;
import com.erik.android.androidlean.bean.NewBean;
import com.erik.android.androidlean.bean.UserBean;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.databinding.ActivityDetailBinding;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.DETAIL_ACTIVITY)
public class DetailActivity extends BaseActivity {

    @Autowired(name = "name")
    public String name;

    @Autowired(name = "user")
    public String user;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        navigationBar();
    }

    private void bindViews() {
        UserBean bean = JSON.parseObject(user, UserBean.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setVariable(BR.user, bean);
    }

    private void navigationBar() {
        NavigationBar navigationBar = binding.navBar;
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

}
