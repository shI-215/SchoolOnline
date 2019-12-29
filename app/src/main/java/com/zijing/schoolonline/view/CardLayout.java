package com.zijing.schoolonline.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.zijing.schoolonline.R;

public class CardLayout extends CardView {
    private ViewHolder viewHolder;
    private Context context;

    public CardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.card_layout, this);
    }

    public void setCard(String title, int image) {
        viewHolder.tv_card_title.setText(title);
        viewHolder.iv_card_image.setImageResource(image);
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView tv_card_title;
        public ImageView iv_card_image;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_card_title = (TextView) rootView.findViewById(R.id.tv_card_title);
            this.iv_card_image = (ImageView) rootView.findViewById(R.id.iv_card_image);
        }

    }
}
