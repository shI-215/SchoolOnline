package com.zijing.schoolonline.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zijing.schoolonline.R;

public class ClickLayout extends RelativeLayout {

    private TextView tv_tag;
    private ImageView iv_next;
    private TextView tv_left_content;
    private TextView tv_right_content;

    public ClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.click_layout, this);
    }

    public ClickLayout setText(String tag, String left_content, String right_content, int contentColor, boolean isShow) {
        init();
        tv_tag.setText(tag);
        tv_left_content.setText(left_content);
        tv_right_content.setText(right_content);
        if (contentColor == 1) tv_left_content.setTextColor(Color.BLUE);
        if (isShow == false) iv_next.setVisibility(GONE);
        return this;
    }

    private void init() {
        tv_tag = (TextView) findViewById(R.id.tv_tag);
        tv_left_content = (TextView) findViewById(R.id.tv_left_content);
        tv_right_content = (TextView) findViewById(R.id.tv_right_content);
        iv_next = (ImageView) findViewById(R.id.iv_next);
    }
}
