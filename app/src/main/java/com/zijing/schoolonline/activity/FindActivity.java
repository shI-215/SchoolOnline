package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.view.FormLayout;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    private FormLayout fl_form;
    private Button btn_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
    }

    private void initView() {
        fl_form = (FormLayout) findViewById(R.id.fl_form);
        btn_find = (Button) findViewById(R.id.btn_find);

        btn_find.setOnClickListener(this);
        fl_form.showCode(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:

                break;
        }
    }
}
