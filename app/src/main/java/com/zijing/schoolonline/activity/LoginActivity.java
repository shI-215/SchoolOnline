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
import com.zijing.schoolonline.view.UserView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UserView {

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
        userPresenter = new UserPresenter(this);
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
//                submit();
                startActivity(new Intent(context, MainActivity.class));
                break;
            case R.id.tv_find:
                startActivity(new Intent(context, NextActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(context, RegisterActivity.class));
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
    public void showSuccess(User user) {
        Toast.makeText(context, "登录成功，用户：" + user.getName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void showFaile() {
        Toast.makeText(context, "登录失败，用户名或者密码错误！！", Toast.LENGTH_SHORT).show();
    }
}
