package com.erik.android.androidlean.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erik.android.androidlean.R;

public class NavigationBar extends LinearLayout implements View.OnClickListener {

    private String titleTextStr;
    private TextView nav_title;
    private ImageView btn_back;
    private ImageView btn_right;
    private boolean isShowBackBtn = false;
    private ButtonOnClickListener btnOnClickListener;

    public NavigationBar(Context context) {
        this(context, null);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TypedArray是一个数组容器用于存放属性值
        @SuppressLint("CustomViewStyleable") TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NaviBar);

        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.NaviBar_titleText:
                    titleTextStr = ta.getString(R.styleable.NaviBar_titleText);
                    break;
            }
        }
        //用完务必回收容器
        ta.recycle();
        View view = LayoutInflater.from(context).inflate(R.layout.nav_bar_layout, this);
        initView(view);
    }

    private void initView(View view) {
        btn_back = view.findViewById(R.id.btn_back);
        nav_title = view.findViewById(R.id.tv_title);
        btn_right = view.findViewById(R.id.btn_right);
        btn_back.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        if (titleTextStr != null) {
            nav_title.setText(titleTextStr);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                if (btnOnClickListener != null) {
                    btnOnClickListener.onBackClick();
                }
                break;
            case R.id.btn_right:
                if (btnOnClickListener != null) {
                    btnOnClickListener.onRightClick();
                }
                break;
        }
    }

    public void setShowBackBtn(boolean showBackBtn) {
        isShowBackBtn = showBackBtn;
        if (showBackBtn) {
            btn_back.setVisibility(VISIBLE);
        } else {
            btn_back.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置标题的点击监听
     *
     * @param btnOnClickListener
     */
    public void setBtnOnClickListener(ButtonOnClickListener btnOnClickListener) {
        this.btnOnClickListener = btnOnClickListener;
    }

    public ImageView getBtn_right() {
        return btn_right;
    }

    public String getTitleTextStr() {
        return titleTextStr;
    }

    public void setTitleTextStr(String titleTextStr) {
        this.titleTextStr = titleTextStr;
        nav_title.setText(titleTextStr);
    }

    /**
     * 监听标题点击接口
     */
    public interface ButtonOnClickListener {
        /**
         * 返回按钮的点击事件
         */
        void onBackClick();
        void onRightClick();
    }
}
