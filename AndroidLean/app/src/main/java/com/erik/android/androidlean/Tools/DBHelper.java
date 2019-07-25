package com.erik.android.androidlean.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.erik.android.androidlean.Bean.Person;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
    }

    public void save(Person person) {
        SQLiteDatabase database = getWritableDatabase();
        //启动事务
        database.beginTransaction();
        try {
            database.execSQL("INSERT INTO person(name,phone) values(?,?)", new String[]{person.getName(),person.getPhone()});
            //设置事务成功
            database.setTransactionSuccessful();
        } finally {
            //结束事务
            database.endTransaction();
        }
    }

    public void delete(Integer id) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM person WHERE personid = ?", new Integer[]{id});
    }

    public void update(Person person) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("UPDATE person SET name = ? , phone = ? WHERE personid = ?", new String[]{person.getName(), person.getPhone(), person.getId().toString()});
    }

    public Person query(Integer id) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM person WHERE personid = ?", new String[]{id.toString()});
        if (cursor.moveToFirst()) {
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            return new Person(personid, name, phone);
        }
        cursor.close();
        return null;
    }

    public List<Person> getScrollData(int offset, int maxResult) {
        List<Person> personList = new ArrayList<Person>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM person ORDER BY personid ASC LIMIT ?,?", new String[]{String.valueOf(offset), String.valueOf(maxResult)});
        while (cursor.moveToNext()) {
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            personList.add(new Person(personid, name, phone));
        }
        cursor.close();
        return personList;
    }

    public long getCount() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT (*) FROM person", null);
        cursor.moveToFirst();
        long result = cursor.getLong(0);
        cursor.close();
        return result;
    }























}
