package com.erik.android.androidlean.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erik.android.androidlean.R;

import java.util.Queue;

public class DBActivity extends AppCompatActivity {

    private NoteDao noteDao;
    private Queue<Note> notesQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
    }

}
