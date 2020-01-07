package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.layout.FormLayout;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FormLayout fl_form;
    private Button btn_register;
    private CheckBox cb_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        fl_form = (FormLayout) findViewById(R.id.fl_form);
        btn_register = (Button) findViewById(R.id.btn_register);
        cb_box = (CheckBox) findViewById(R.id.cb_box);

        btn_register.setOnClickListener(this);
        fl_form.showCode(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:

                break;
        }
    }
}
