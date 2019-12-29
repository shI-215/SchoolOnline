package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;

public class MainActivity extends AppCompatActivity {

    private EditText edt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edt_text = (EditText) findViewById(R.id.edt_text);
    }

    private void submit() {
        // validate
        String text = edt_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "text不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
