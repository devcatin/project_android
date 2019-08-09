package com.erik.android.androidlean.ohter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.erik.android.androidlean.tool.DBOpenHelper;

public class NameContentProvider extends ContentProvider {

    private DBOpenHelper openHelper;
    public static final int USER_INFO = 1;
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(UserInfoContent.AUTHORITIES, "/test", USER_INFO);
    }

    @Override
    public boolean onCreate() {
        openHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        if (matcher.match(uri) == USER_INFO) {
            SQLiteDatabase database = openHelper.getReadableDatabase();
            cursor = database.query(UserInfoContent.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case USER_INFO:
                SQLiteDatabase database = openHelper.getWritableDatabase();
                long rowId = database.insert(UserInfoContent.TABLE_NAME, null, values);
                if (rowId > 0) {
                    Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                    getContext().getContentResolver().notifyChange(nameUri, null);
                    return nameUri;
                }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        if (matcher.match(uri) == USER_INFO) {
            SQLiteDatabase database = openHelper.getWritableDatabase();
            count = database.delete(UserInfoContent.TABLE_NAME, selection, selectionArgs);
            database.close();
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return "";
    }

}
