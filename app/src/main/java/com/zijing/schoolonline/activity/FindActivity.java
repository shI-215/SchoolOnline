package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.util.RegexUtil;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = "FindActivity";
    String country = "86";
    private long exitTime = 0;

    private EditText edt_phone;
    private EditText edt_code;
    private TextView tv_get;
    private LinearLayout ll_code;
    private Button btn_next;
    private LinearLayout ll_one;
    private TextView tv_phone;
    private EditText edt_pwd;
    private Button btn_find;
    private LinearLayout ll_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void initView() {
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_get = (TextView) findViewById(R.id.tv_get);
        ll_code = (LinearLayout) findViewById(R.id.ll_code);
        btn_next = (Button) findViewById(R.id.btn_next);
        ll_one = (LinearLayout) findViewById(R.id.ll_one);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        btn_find = (Button) findViewById(R.id.btn_find);
        ll_two = (LinearLayout) findViewById(R.id.ll_two);

        tv_get.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_next.setEnabled(false);
        btn_find.setOnClickListener(this);

        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btn_next.setEnabled(false);
                } else {
                    btn_next.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get:
                getCode();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.btn_find:
                submit();
                break;
        }
    }

    //获取验证码
    private void getCode() {
        String phone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!RegexUtil.regTelPhoneNumber(phone)) {
                Toast.makeText(this, "电话号码不正确", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        SMSSDK.getVerificationCode(country, phone);
    }

    private void next() {
        // validate
        String phone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!RegexUtil.regTelPhoneNumber(phone)) {
                Toast.makeText(this, "电话号码不正确", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String code = edt_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something

        SMSSDK.submitVerificationCode(country, phone, code);
    }

    //提交
    private void submit() {
        // validate
        String phone = tv_phone.getText().toString();
        String pwd = edt_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!RegexUtil.regPassword(pwd)) {
                Toast.makeText(this, "请输入8-16位由字母和数字组成的密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // TODO validate success, do something
    }

    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            handler.sendEmptyMessage(1);
                        } else {
                            // TODO 处理错误的结果
                            handler.sendEmptyMessage(4);
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            handler.sendEmptyMessage(2);
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(FindActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                    countDownTimer.start();
                    break;
                case 2:
                    Toast.makeText(FindActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    ll_one.setVisibility(View.GONE);
                    ll_two.setVisibility(View.VISIBLE);
                    tv_phone.setText(edt_phone.getText().toString());
                    break;
                case 3:
                    ll_one.setVisibility(View.VISIBLE);
                    ll_two.setVisibility(View.GONE);
                    break;
                case 4:
                    Toast.makeText(FindActivity.this, "操作频繁，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //倒计时务
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_get.setText(millisUntilFinished / 1000 + "");
            tv_get.setClickable(false);
        }

        @Override
        public void onFinish() {
            tv_get.setText("重新获取");
            tv_get.setClickable(true);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                handler.sendEmptyMessage(3);
                exitTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

//    @Override
//    public void findSuccess(User user) {
//        Toast.makeText(FindActivity.this, "找回密码成功", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(FindActivity.this, LoginActivity.class));
//        finish();
//    }
}
