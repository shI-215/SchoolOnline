package com.zijing.schoolonline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.App;
import com.zijing.schoolonline.MainActivity;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.presenter.UserPresenter;
import com.zijing.schoolonline.presenter.UserPresenterImpl;
import com.zijing.schoolonline.util.RegexUtil;
import com.zijing.schoolonline.util.ToastUtil;
import com.zijing.schoolonline.view.MyListening;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class NextActivity extends AppCompatActivity implements View.OnClickListener, MyListening {

    private SharedPreferences preferences = App.myContext.getSharedPreferences(App.SP_NAME,
            App.myContext.MODE_PRIVATE);
    private UserPresenter userPresenter;
    public static AppCompatActivity activity;
    private Context context;

    private String phone;
    private int titleType;
    private int updatePhone;

    private EditText edt_phone;
    private EditText edt_code;
    private TextView tv_get;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titleType = getIntent().getIntExtra("titleType", 0);
        updatePhone = getIntent().getIntExtra("updatePhone", 0);
        if (titleType == 1) {
            setTitle(App.REGISTER_VALUE);
        } else if (titleType == 2) {
            setTitle(App.FINDV_ALUE);
        }
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
        userPresenter = new UserPresenterImpl(this);
        activity = this;
        context = this;
        initView();
    }

    private void initView() {
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btn_next = (Button) findViewById(R.id.btn_next);

        tv_get.setOnClickListener(this);
        btn_next.setOnClickListener(this);
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
                submit();
                break;
        }
    }

    //获取验证码
    private void getCode() {
        phone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!RegexUtil.regTelPhoneNumber(phone)) {
                Toast.makeText(this, "电话号码不正确", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        SMSSDK.getVerificationCode(App.MOB_COUNTRY, phone);
    }

    private void submit() {
        // validate
        phone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "电话号码", Toast.LENGTH_SHORT).show();
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

        SMSSDK.submitVerificationCode(App.MOB_COUNTRY, phone, code);
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
                            handler.sendEmptyMessage(3);
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            handler.sendEmptyMessage(2);
                        } else {
                            // TODO 处理错误的结果
                            handler.sendEmptyMessage(4);
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
                    Toast.makeText(NextActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                    edt_phone.clearFocus();//清除焦点，光标消失
                    edt_code.requestFocus();//获取焦点，光标出现
                    edt_code.setHint("请输入验证码");
                    countDownTimer.start();
                    break;
                case 2:
                    if (updatePhone != 0) {
                        userPresenter.alterPhone(phone);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("phone", phone);
                        intent.putExtra("titleType", titleType);
                        intent.setClass(context, FinishActivity.class);
                        startActivity(intent);
                    }
                    break;
                case 3:
                    Toast.makeText(NextActivity.this, "操作频繁，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(NextActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    ToastUtil.l(NextActivity.this, "请检查网络");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(Object object) {
        Toast.makeText(NextActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        MainActivity.compatActivity.finish();
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(Object object) {
        Toast.makeText(NextActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
        userPresenter.onDestroy();
    }
}
