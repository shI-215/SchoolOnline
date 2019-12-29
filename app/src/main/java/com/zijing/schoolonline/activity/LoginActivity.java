package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.view.FormLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private ImageView iv_logo;
    private FormLayout fl_form;
    private Button btn_login;
    private TextView tv_find;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initView();
    }

    private void initView() {
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        fl_form = (FormLayout) findViewById(R.id.fl_form);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_find = (TextView) findViewById(R.id.tv_find);
        tv_register = (TextView) findViewById(R.id.tv_register);

        btn_login.setOnClickListener(this);
        tv_find.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_login:
                intent.setClass(context, MainActivity.class);
                break;
            case R.id.tv_find:
                intent.setClass(context, FindActivity.class);
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                break;
        }
        startActivity(intent);
    }
}
