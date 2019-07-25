package com.erik.android.androidlean.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.erik.android.androidlean.R;
import com.erik.android.androidlean.Tools.DBHelper;

public class BuyFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private StringBuilder builder;
    private  int index = 1;

    public BuyFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(context, "test.db", null, 1);
    }

    private void bindViews(View view) {
        Button btn_insert = view.findViewById(R.id.btn_insert);
        Button btn_query = view.findViewById(R.id.btn_query);
        Button btn_update = view.findViewById(R.id.btn_update);
        Button btn_delete = view.findViewById(R.id.btn_delete);

        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        bindViews(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        database = dbHelper.getWritableDatabase();
        int tag = view.getId();
        switch (tag) {
            case R.id.btn_insert:
                ContentValues values = new ContentValues();
                values.put("name", "呵呵~" + index);
                index++;
                database.insert("person", null, values);
                Toast.makeText(context, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query:
                builder = new StringBuilder();
                Cursor cursor = database.query("person", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        int pid = cursor.getInt(cursor.getColumnIndex("personid"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        builder.append("id:" + pid + ":" + name + "\n");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(context, builder.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                ContentValues values1 = new ContentValues();
                values1.put("name","呵呵~");
                database.update("person", values1, "name = ?", new String[]{"呵呵~2"});
                break;
            case R.id.btn_delete:
                database.delete("person", "personid = ?", new String[]{"3"});
                break;
        }
    }

}
