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

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper openHelper;

    static {
        matcher.addURI("com.jay.example.providers.myprovider", "test", 1);
    }

    @Override
    public boolean onCreate() {
        openHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case 1:
                SQLiteDatabase database = openHelper.getReadableDatabase();
                long rowId = database.insert("test", null, values);
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
        return 0;
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
