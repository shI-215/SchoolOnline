package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.MainActivity;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.presenter.UserPresenter;
import com.zijing.schoolonline.presenter.UserPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private Context context;
    private UserPresenter userPresenter;

    private EditText edt_phone;
    private EditText edt_pwd;
    private Button btn_login;
    private TextView tv_find;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        userPresenter = new UserPresenterImpl(this);
        initView();
    }

    private void initView() {
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_find = (TextView) findViewById(R.id.tv_find);
        tv_register = (TextView) findViewById(R.id.tv_register);

        btn_login.setOnClickListener(this);
        tv_find.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_find:
                Intent intent = new Intent();
                intent.putExtra("titleType", 2);
                intent.setClass(context, NextActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_register:
                Intent intent2 = new Intent();
                intent2.putExtra("titleType", 1);
                intent2.setClass(context, NextActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void submit() {
        // validate
        String phone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "电话号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = edt_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        userPresenter.userLogin(phone, pwd);
    }

    @Override
    public void onSuccess(User user) {
        SharedPreferencesUtil.put(context, "userId", user.getUserId());
        SharedPreferencesUtil.put(context, "userPhone", user.getUserPhone());
        SharedPreferencesUtil.put(context, "userName", user.getUserName());
        SharedPreferencesUtil.put(context, "userAutograph", user.getUserAutograph());

        SharedPreferencesUtil.put(context, "waterId", user.getWater().getWaterId());
        SharedPreferencesUtil.put(context, "roomId", user.getRoom().getRoomId());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed() {
        Toast.makeText(context, "登录失败，用户名或者密码错误！！", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        userPresenter.onDestroy();
        super.onDestroy();
    }
}