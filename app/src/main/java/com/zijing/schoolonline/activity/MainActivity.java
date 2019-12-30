package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.view.CardLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private CardLayout cl_air;
    private CardLayout cl_elect;
    private CardLayout cl_water;
    private Button btn_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        cl_air.setCard("空调", R.drawable.air);
        cl_elect.setCard("电费", R.drawable.elect);
        cl_water.setCard("水费", R.drawable.water);
    }

    private void initView() {
        cl_air = (CardLayout) findViewById(R.id.cl_air);
        cl_elect = (CardLayout) findViewById(R.id.cl_elect);
        cl_water = (CardLayout) findViewById(R.id.cl_water);
        btn_personal = (Button) findViewById(R.id.btn_personal);
        cl_air.setOnClickListener(this);
        cl_elect.setOnClickListener(this);
        cl_water.setOnClickListener(this);
        btn_personal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_air:
                startActivity(new Intent(context, RechargeActivity.class));
                break;
            case R.id.cl_elect:
                startActivity(new Intent(context, PersonalActivity.class));
                break;
            case R.id.cl_water:
                startActivity(new Intent(context, PersonalActivity.class));
                break;
            case R.id.btn_personal:
                startActivity(new Intent(context, PersonalActivity.class));
                break;
        }
    }
}
