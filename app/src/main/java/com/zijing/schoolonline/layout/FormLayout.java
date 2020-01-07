package com.zijing.schoolonline.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zijing.schoolonline.R;

public class FormLayout extends LinearLayout {
    private Context context;
    private EditText edt_phone;
    private EditText edt_pwd;
    private EditText edt_code;
    private TextView tv_get;
    private LinearLayout ll_code;

    public FormLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.form_layout, this);
        this.context = context;
    }

    //提交
    public void submit() {
        String phone = edt_phone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            HelloToast("电话不能为空");
            return;
        }
        String password = edt_pwd.getText().toString();
        if (TextUtils.isEmpty(password)) {
            HelloToast("密码不能为空");
            return;
        }
        //判断获取验证码是否可见
        if (ll_code.getVisibility() == View.VISIBLE) {
            String code = edt_code.getText().toString();
            if (TextUtils.isEmpty(code)) {
                HelloToast("验证码不能为空");
                return;
            }
        }
    }

    //设置验证码显示
    public FormLayout showCode(boolean isGetCode) {
        init();
        if (isGetCode == true) ll_code.setVisibility(VISIBLE);
        return this;
    }

    private void init() {
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_get = (TextView) findViewById(R.id.tv_get);
        ll_code = (LinearLayout) findViewById(R.id.ll_code);
    }

    //提示
    private void HelloToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
