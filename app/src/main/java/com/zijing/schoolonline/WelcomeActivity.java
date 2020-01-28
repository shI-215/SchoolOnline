package com.zijing.schoolonline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.activity.LoginActivity;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.presenter.RegisterPresenter;
import com.zijing.schoolonline.presenter.RegisterPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.view.RegisterView;

public class WelcomeActivity extends AppCompatActivity implements RegisterView {

    private RegisterPresenter registerPresenter;
    private Context context;

    private TextView tv_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        context = this;
        registerPresenter = new RegisterPresenterImpl(this);
        //启动倒计时任务
        countDownTimer.start();
    }

    //倒计时务
    private CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_timer.setText(millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {
            Log.v("welcomge", "hello");
            //判断用户是否登录
            String phone = (String) SharedPreferencesUtil.get(context, "userPhone", "");
            Log.v("welcomge0", phone);
            if (TextUtils.isEmpty(phone)) {
                Log.v("welcomge1", phone);
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            } else {
                Log.v("welcomge2", phone);
//                registerPresenter.userGetUser(phone);
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }
    };

    private void initView() {
        tv_timer = (TextView) findViewById(R.id.tv_timer);
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(Message message) {
        Log.v("welcome", message.toString());
        Toast.makeText(context, message.getData(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }
}
