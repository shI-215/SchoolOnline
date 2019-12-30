package com.zijing.schoolonline.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zijing.schoolonline.R;

public class ClickLayout extends RelativeLayout {
    private Context context;
    private TextView tv_tag;
    private TextView tv_content;
    private ImageView iv_next;

    public ClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.click_layout, this);
        this.context = context;
    }

    public ClickLayout setText(String tag, String content, boolean isShow) {
        init();
        tv_tag.setText(tag);
        tv_content.setText(content);
        if (isShow == false) iv_next.setVisibility(GONE);
        return this;
    }

    private void init() {
        tv_tag = (TextView) findViewById(R.id.tv_tag);
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_next = (ImageView) findViewById(R.id.iv_next);
    }
}
