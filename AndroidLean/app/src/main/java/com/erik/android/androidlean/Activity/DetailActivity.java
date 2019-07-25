package com.erik.android.androidlean.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erik.android.androidlean.Tools.SharedHelper;
import com.erik.android.androidlean.R;

import java.util.Map;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editname;
    private EditText editdetail;
    private Button btnsave;
    private Button btnclean;
    private Button btnread;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView tv_title = findViewById(R.id.txt_topbar);
        tv_title.setText(title);

        mContext = getApplicationContext();
        bindViews();
    }

    private void bindViews() {
        editdetail = findViewById(R.id.editdetail);
        editname = findViewById(R.id.editname);
        btnclean = findViewById(R.id.btnclean);
        btnsave = findViewById(R.id.btnsave);
        btnread = findViewById(R.id.btnread);

        btnclean.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnclean:
                editdetail.setText("");
                editname.setText("");
                break;
            case R.id.btnsave:
                SharedHelper sharedHelper = new SharedHelper(mContext);
                String filename = editname.getText().toString();
                String filedetail = editdetail.getText().toString();
                try {
                    sharedHelper.saveData(filename, filedetail);
                    Toast.makeText(mContext, "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnread:
                SharedHelper sharedHelper1 = new SharedHelper(mContext);
                Map<String, String> map = sharedHelper1.readData();
                String value = "suername:" + map.get("username") + " passwd:" + map.get("passwd");
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
