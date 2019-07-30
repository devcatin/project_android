package com.erik.utilslibrary.FileDownload;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

public class FileService {

    private DBOpenHelper openHelper;

    public FileService(Context context) {
        openHelper = new DBOpenHelper(context);
    }

    public Map<Integer, Integer> getData(String path) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select threadid, downlength from filedownlog where downpath=?", new String[]{path});
        Map<Integer, Integer> data = new HashMap<Integer, Integer>();
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            data.put(cursor.getInt(0), cursor.getInt(1));
            data.put(cursor.getInt(cursor.getColumnIndexOrThrow("threadid")), cursor.getInt(cursor.getColumnIndexOrThrow("downlength")));
        }
        cursor.close();
        database.close();
        return data;
    }

    public void save(String path, Map<Integer, Integer> map) {
        SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                database.execSQL("insert into filedownlog(downpath, threadid, downlength) values(?,?,?)", new Object[]{path, entry.getKey(), entry.getValue()});
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        database.close();
    }

    public void update(String path, int threadId, int pos) {
        SQLiteDatabase database = openHelper.getWritableDatabase();
        database.execSQL("update filedownlog set downlength=? where downpath=? and threadid=?", new Object[]{pos, path, threadId});
        database.close();
    }

    public void delete(String path) {
        SQLiteDatabase database = openHelper.getWritableDatabase();
        database.execSQL("delete from filedownlog where downpath=?", new Object[]{path});
        database.close();
    }

}






































