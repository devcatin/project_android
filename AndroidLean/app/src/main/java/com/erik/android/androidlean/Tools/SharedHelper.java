package com.erik.android.androidlean.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SharedHelper {

    private Context context;

    public SharedHelper() {

    }

    public SharedHelper(Context context) {
        this.context = context;
    }

    public void saveData(String username, String passwd) {
        SharedPreferences preferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        Toast.makeText(context, "信息已写入SharedPreferences中", Toast.LENGTH_SHORT).show();
    }

    public Map<String, String> readData() {
        Map<String, String> map = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        map.put("username", preferences.getString("username", ""));
        map.put("passwd", preferences.getString("passwd", ""));
        return map;
    }
}
