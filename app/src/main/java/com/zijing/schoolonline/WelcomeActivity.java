package com.zijing.schoolonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.activity.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Context context;

    private TextView tv_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        context = this;
        //启动倒计时任务
        countDownTimer.start();
    }

    private void initView() {
        tv_timer = (TextView) findViewById(R.id.tv_timer);
    }

    //倒计时务
    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_timer.setText(millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {
            SharedPreferences preferences = context.getSharedPreferences(App.SP_NAME, context.MODE_PRIVATE);
            String name = preferences.getString("name", "");
            //判断用户是否登录
            if (TextUtils.isEmpty(name)) {
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            } else {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }
    };
}
