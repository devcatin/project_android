package com.erik.android.androidlean.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erik.android.androidlean.R;
import com.erik.android.androidlean.constant.ConstConfig;
import com.erik.android.androidlean.view.DrawingBoard;
import com.erik.android.androidlean.view.NavigationBar;
import com.erik.utilslibrary.ActivityManager;

@Route(path = ConstConfig.DRAWBOARD_ACTIVITY)
public class DrawBoardActivity extends BaseActivity {

    private DrawingBoard draw_board;

    @Autowired(name = "name")
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_board);

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
        draw_board = findViewById(R.id.draw_board);
        Button clean_board = findViewById(R.id.btn_clean_board);
        clean_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_board.clear();
            }
        });
    }

}
