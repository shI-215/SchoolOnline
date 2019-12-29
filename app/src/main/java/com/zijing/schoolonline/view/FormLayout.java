package com.zijing.schoolonline.view;

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
    private ViewHolder viewHolder;

    public FormLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.form_layout, this);
    }

    //提交
    public void submit() {
        String phone = viewHolder.edt_phone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            HelloToast("电话不能为空");
            return;
        }
        String password = viewHolder.edt_pwd.getText().toString();
        if (TextUtils.isEmpty(password)) {
            HelloToast("密码不能为空");
            return;
        }
        //判断获取验证码是否可见
        if (viewHolder.ll_code.getVisibility() == View.VISIBLE) {
            String code = viewHolder.edt_code.getText().toString();
            if (TextUtils.isEmpty(code)) {
                HelloToast("验证码不能为空");
                return;
            }
        }
    }

    //设置验证码显示
    public void setVisibility() {
        viewHolder.ll_code.setVisibility(VISIBLE);
    }

    //提示
    private void HelloToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static
    class ViewHolder {
        public View rootView;
        public EditText edt_phone;
        public EditText edt_pwd;
        public EditText edt_code;
        public TextView tv_get;
        public LinearLayout ll_code;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.edt_phone = (EditText) rootView.findViewById(R.id.edt_phone);
            this.edt_pwd = (EditText) rootView.findViewById(R.id.edt_pwd);
            this.edt_code = (EditText) rootView.findViewById(R.id.edt_code);
            this.tv_get = (TextView) rootView.findViewById(R.id.tv_get);
            this.ll_code = (LinearLayout) rootView.findViewById(R.id.ll_code);
        }
    }
}
