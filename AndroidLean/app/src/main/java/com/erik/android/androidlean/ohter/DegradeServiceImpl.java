package com.erik.android.androidlean.ohter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

@Route(path = "/degrade/service")
public class DegradeServiceImpl implements DegradeService {

    Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void onLost(Context context, Postcard postcard) {
        Toast.makeText( context,"找不到路径" + postcard.getPath() ,Toast.LENGTH_SHORT).show();
    }

}
