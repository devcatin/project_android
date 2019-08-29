package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.bean.MessageEvent;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //声明控件对象
    private EditText et_name,et_pass;
    private Button mLoginButton,mLoginError,mRegister;
    private int SERVER_FLAG = 0;

    @Autowired(name = "name")
    public String name;

    private final static int LOGIN_ENABLE = 0x01;
    private final static int LOGIN_UNABLF = 0x02;
    private final static int PASS_ERROR =  0x03;
    private final static int NAME_ERROR = 0x04;  //上面是消息的常量值

    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private CheckBox bt_pwd_eye;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;  //文本监视器

    final Handler UiMangerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case LOGIN_ENABLE:
                    mLoginButton.setClickable(true);
                    break;
                case LOGIN_UNABLF:
                    mLoginButton.setClickable(false);
                    break;
                case PASS_ERROR:
                    break;
                case NAME_ERROR:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        navigationBar();
        bindViews();
    }

    private void bindViews() {
        et_name = findViewById(R.id.usename);
        et_pass = findViewById(R.id.password);
        bt_username_clear = findViewById(R.id.bt_usename_clear);
        bt_pwd_clear = findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = findViewById(R.id.bt_pwd_eyes);

        initWatcher();

        bt_username_clear.setOnClickListener(this);
        bt_pwd_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        bt_pwd_clear.setOnClickListener(this);
        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);

        mLoginButton = findViewById(R.id.login);
        mLoginError  = findViewById(R.id.login_error);
        mRegister    = findViewById(R.id.register);
        mLoginButton.setOnClickListener(this);
        mLoginError.setOnClickListener(this);
        mRegister.setOnClickListener(this);
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

    private void initWatcher() {
        username_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    bt_username_clear.setVisibility(View.VISIBLE);
                } else {
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
        password_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                } else {
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //登陆activity
            case R.id.login:
                login();
                break;
            //忘记密码
            case R.id.login_error:
                break;
            //注册
            case R.id.register:
                break;
            case R.id.bt_usename_clear:
                et_name.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
        }
    }

    private void login() {

    }

    /**
     * 监听back的那块
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK) {

        }
        return super.onKeyDown(keyCode, event);
    }

    TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {//表示最终内容
            Log.d("afterTextChanged", s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start/*开始的位置*/, int count/*被改变的旧内容数*/, int after/*改变后的内容数量*/) {
            //这里的s表示改变之前的内容，通常start和count组合，可以在s中读取本次改变字段中被改变的内容。而after表示改变后新的内容的数量。
        }

        @Override
        public void onTextChanged(CharSequence s, int start/*开始位置*/, int before/*改变前的内容数量*/, int count/*新增数*/) {
            //这里的s表示改变之后的内容，通常start和count组合，可以在s中读取本次改变字段中新的内容。而before表示被改变的内容的数量。
        }
    };

}
