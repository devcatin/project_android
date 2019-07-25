package com.erik.android.androidlean.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erik.android.androidlean.Bean.DaoSession;
import com.erik.android.androidlean.Bean.Note;
import com.erik.android.androidlean.Bean.NoteDao;
import com.erik.android.androidlean.Ohters.TestApplication;
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
