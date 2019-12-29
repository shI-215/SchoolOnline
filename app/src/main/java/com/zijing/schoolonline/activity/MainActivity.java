package com.zijing.schoolonline.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.view.CardLayout;

public class MainActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHolder.cl_air.setCard("空调", R.drawable.air);
        viewHolder.cl_elect.setCard("电费", R.drawable.elect);
        viewHolder.cl_water.setCard("水费", R.drawable.water);
    }

    public static
    class ViewHolder {
        public View rootView;
        public CardLayout cl_air;
        public CardLayout cl_elect;
        public CardLayout cl_water;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.cl_air = (CardLayout) rootView.findViewById(R.id.cl_air);
            this.cl_elect = (CardLayout) rootView.findViewById(R.id.cl_elect);
            this.cl_water = (CardLayout) rootView.findViewById(R.id.cl_water);
        }

    }
}
