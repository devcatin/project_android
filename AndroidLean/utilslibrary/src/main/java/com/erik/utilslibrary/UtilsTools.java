package com.erik.utilslibrary;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UtilsTools {

    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = read(inputStream);
        inputStream.close();
        return bytes;
    }

    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = read(inputStream);
            String html = new String(bytes, "UTF-8");
            return html;
        }
        return null;
    }

    private static byte[] read(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] butffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(butffer)) != -1) {
            outputStream.write(butffer, 0, len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }

    public static String loginPost(String number, String passwd) throws Exception {
        String msg = "";
        try {
            String path = "http://www.baidu.com";
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            String data = "passwd=" + URLEncoder.encode(passwd, "UTF-8") + "&number=" + URLEncoder.encode(number, "UTF-8");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = inputStream.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }
                inputStream.close();
                msg = new String((message.toByteArray()));
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void parseEasyJson(String json) throws JSONException {
        List<TestBean> list = new ArrayList<TestBean>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = (JSONObject)jsonArray.get(index);
                TestBean person = new TestBean();
                person.setNew_title(jsonObject.getString("new_title"));
                person.setNew_content(jsonObject.getString("new_content"));
                list.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parseDiffJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("ch");
            Log.e("Json", json);
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject1 = (JSONObject)jsonArray.get(index);
                String name = jsonObject.getString("names");
                JSONArray jsonArray1 = jsonObject1.getJSONArray("data");
                JSONArray jsonArray2 = jsonObject1.getJSONArray("times");
                Log.e("Json", name);
                Log.e("Json", jsonArray1.toString());
                Log.e("Json", jsonArray2.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void download(String path, Context context) throws Exception {
        URL url = new URL(path);
        InputStream inputStream = url.openStream();
        String end = path.substring(path.lastIndexOf("."));
        OutputStream outputStream = context.openFileOutput("Cache_" + System.currentTimeMillis() + end, Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }






















}
