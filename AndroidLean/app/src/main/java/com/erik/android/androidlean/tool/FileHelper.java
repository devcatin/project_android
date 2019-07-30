package com.erik.android.androidlean.tool;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {

    private Context mContext;

    public FileHelper() {

    }

    public FileHelper(Context context){
        super();
        this.mContext = context;
    }

    public void saveFileToSD(String filename, String filecontent) throws Exception {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            FileOutputStream outputStream = new FileOutputStream(filename);
            outputStream.write(filecontent.getBytes());
            outputStream.close();
        } else {
            Toast.makeText(mContext, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFromSD(String filename) throws IOException {
        StringBuffer buffer = new StringBuffer("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            FileInputStream inputStream = new FileInputStream(filename);
            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) > 0) {
                buffer.append(new String(temp, 0, len));
            }
            inputStream.close();
        }
        return buffer.toString();
    }

    public void saveFile(String filename, String filecontent) throws Exception {
        FileOutputStream outputStream = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(filecontent.getBytes());
        outputStream.close();
    }

    public String readFile(String filename) throws IOException {
        FileInputStream inputStream = mContext.openFileInput(filename);
        byte[] temp = new byte[1024];
        StringBuffer buffer = new StringBuffer("");
        int len = 0;
        while ((len = inputStream.read(temp)) > 0) {
            buffer.append(new String(temp, 0, len));
        }
        inputStream.close();
        return buffer.toString();
    }

}
