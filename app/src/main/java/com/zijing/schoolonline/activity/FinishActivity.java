package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.presenter.RegisterPresenter;
import com.zijing.schoolonline.presenter.RegisterPresenterImpl;
import com.zijing.schoolonline.util.RegexUtil;
import com.zijing.schoolonline.view.RegisterView;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {

    private RegisterPresenter registerPresenter;
    private Context context;

    private String phone;
    private int titleType;

    private TextView tv_phone;
    private EditText edt_pwd;
    private Button btn_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titleType = getIntent().getIntExtra("titleType", 0);
        phone = getIntent().getStringExtra("phone");
        if (titleType == 1) {
            setTitle(ApplicationParam.REGISTER_VALUE);
        } else if (titleType == 2) {
            setTitle(ApplicationParam.FINDV_ALUE);
        }
        context = this;
        registerPresenter = new RegisterPresenterImpl(this);
        initView();
    }

    private void initView() {
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        btn_find = (Button) findViewById(R.id.btn_find);

        tv_phone.setText(phone);
        btn_find.setOnClickListener(this);
        edt_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btn_find.setEnabled(false);
                } else {
                    btn_find.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String pwd = edt_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!RegexUtil.regPassword(pwd)) {
                Toast.makeText(this, "请输入8-16位由字母和数字组成的密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        // TODO validate success, do something
        if (titleType == 1) {//用户注册
            registerPresenter.userRegister(phone, pwd);
        } else if (titleType == 2) {//找回密码
            registerPresenter.userFind(phone, pwd);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess() {

        if (titleType == 1) {//用户注册
            Toast.makeText(context, "注册成功！", Toast.LENGTH_LONG).show();
        } else if (titleType == 2) {//找回密码
            Toast.makeText(context, "找回密码成功！", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(context, LoginActivity.class));
        NextActivity.activity.finish();
        finish();
    }

    @Override
    public void onFailed(Message message) {
//        if (titleType == 1) {//用户注册
//            ToastUtil.l(message.getMsg());
//        } else if (titleType == 2) {//找回密码
//            ToastUtil.l("找回密码成功！");
//        }
        Log.v("FinishActivity::::::", message.toString());
        Toast.makeText(context, message.getData(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.onDestroy();
    }
}
