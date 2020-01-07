package com.zijing.schoolonline.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zijing.schoolonline.R;

public class InfoLayout extends LinearLayout {
    private Context context;
    private TextView tv_account;
    private TextView tv_balance;

    public InfoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.info_layout, this);
        this.context = context;
    }

    public InfoLayout setInformation(String account, String balance) {
        init();
        tv_account.setText(account);
        tv_balance.setText(balance);
        return this;
    }

    private void init() {
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
    }
}
