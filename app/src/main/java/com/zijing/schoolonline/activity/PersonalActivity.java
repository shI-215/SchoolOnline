package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.view.ClickLayout;

public class PersonalActivity extends AppCompatActivity {

    private ImageView iv_user_image;
    private TextView tv_user_name;
    private TextView tv_user_signature;
    private ClickLayout cl_phone;
    private ClickLayout cl_birthday;
    private ClickLayout cl_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
    }

    private void initView() {
        iv_user_image = (ImageView) findViewById(R.id.iv_user_image);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_signature = (TextView) findViewById(R.id.tv_user_signature);
        cl_phone = (ClickLayout) findViewById(R.id.cl_phone);
        cl_birthday = (ClickLayout) findViewById(R.id.cl_birthday);
        cl_room = (ClickLayout) findViewById(R.id.cl_room);

        cl_phone.setText("联系电话", "12345678900", true);
        cl_birthday.setText("出生日期", "2019-12-30", true);
        cl_room.setText("宿舍信息", "一栋-101", false);
    }
}
