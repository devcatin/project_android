package com.erik.android.androidlean.ohter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/helloService/hello", name = "test service")
public class HelloServiceImpl implements HelloService {

    @Override
    public void init(Context context) {

    }

    @Override
    public String sayHello(String name) {
        Log.i("HelloServiceImpl", name);
        return "Hello , " + name;
    }
}
