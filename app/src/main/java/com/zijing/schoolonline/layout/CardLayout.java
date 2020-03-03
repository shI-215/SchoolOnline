package com.zijing.schoolonline.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.zijing.schoolonline.R;

public class CardLayout extends CardView {
    private Context context;
    private TextView tv_card_title;
    private ImageView iv_card_image;
    private CardView card;

    public CardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.card_layout, this);
        this.context = context;
    }

    public void setCard(int color, String title, int image) {
        init();
        card.setCardBackgroundColor(color);
        tv_card_title.setText(title);
        iv_card_image.setImageResource(image);
    }

    private void init() {
        card = findViewById(R.id.card);
        tv_card_title = findViewById(R.id.tv_card_title);
        iv_card_image = findViewById(R.id.iv_card_image);
    }
}
