package com.erik.android.androidlean.bean;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.erik.android.androidlean.R;

public class TestObject {

    private Context context;

    public TestObject(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("联系人列表").setIcon(R.mipmap.ic_launcher)
                .setItems(new String[]{"基神","啥大嫁风尚","是的发生打开了","爱上了对方吗","收到了发了"}, null)
                .setPositiveButton("确定", null).create().show();
    }

}
