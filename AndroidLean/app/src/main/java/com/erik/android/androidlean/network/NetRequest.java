package com.erik.android.androidlean.network;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetRequest {

    private OkHttpClient client;
    private static NetRequest netRequest;
    private Context context;

    private NetRequest(Context context) {
        this.context = context;
        client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    public static NetRequest getInstance(Context context) {
        if (netRequest == null) {
            netRequest = new NetRequest(context);
        }
        return netRequest;
    }

    //get请求封装
    public void get(String url, HashMap<String, String> param, final RequestCallBack requestCallBack) {
        if (!param.isEmpty()) {
            StringBuffer buffer = new StringBuffer(url);
            buffer.append('?');
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append('=');
                buffer.append(entry.getValue());
                buffer.append('&');
            }
            buffer.deleteCharAt(buffer.length()-1);
            url = buffer.toString();
        }
        Request.Builder builder = new Request.Builder().url(url);
        builder.method("GET", null);
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                requestCallBack.failure(e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                requestCallBack.success(response);
            }
        });
    }

    public void get(String url, RequestCallBack requestCallBack) {
        get(url, new HashMap<String, String>(), requestCallBack);
    }

    //post 请求封装
    public void post(String url, HashMap<String, String> param, final RequestCallBack requestCallBack) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (!param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = formBody.build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(requestBody)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                requestCallBack.failure(e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                requestCallBack.success(response);
            }
        });
    }

    public interface RequestCallBack {
        void success(Response response) throws IOException;
        void failure(IOException e);
    }

}

















