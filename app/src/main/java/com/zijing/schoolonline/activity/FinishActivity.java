package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.util.RegexUtil;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {

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
        titleType = getIntent().getIntExtra("titleType", 0);
        Log.v("FinishActivity", titleType + "");
        phone = getIntent().getStringExtra("phone");
        if (titleType == 1) {
            setTitle(ApplicationParam.REGISTER_VALUE);
        } else if (titleType == 2) {
            setTitle(ApplicationParam.FINDV_ALUE);
        }
        context = this;
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

        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NextActivity.activity.finish();
    }
}
