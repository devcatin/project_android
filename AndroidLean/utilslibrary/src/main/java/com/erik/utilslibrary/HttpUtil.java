package com.erik.utilslibrary;

import android.net.ConnectivityManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    public static final String TAG = "HttpUtil";
    private static  HttpUtil httpUtil = null;
    private static OkHttpClient httpClient;

    public static synchronized HttpUtil getInstance() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class) {
                httpUtil = new HttpUtil();
            }
        }
        return httpUtil;
    }

    private HttpUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        builder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        builder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        httpClient = builder.build();
    }

    private void get(String url, final ConnectivityManager.NetworkCallback callback) {

    }

}
