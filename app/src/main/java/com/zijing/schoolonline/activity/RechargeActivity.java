package com.zijing.schoolonline.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.adapter.RechargeAdapter;
import com.zijing.schoolonline.view.InfoLayout;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> list = new ArrayList<>();
    private Context context;
    private RechargeAdapter rechargeAdapter;
    private int index;
    private int[] money;

    private InfoLayout il_info;
    private RecyclerView rv_recharge;
    private Button btn_recharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
        context = this;
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv_recharge.setLayoutManager(layoutManager);
        rechargeAdapter = new RechargeAdapter(list);
        rv_recharge.setAdapter(rechargeAdapter);
    }

    private void initView() {
        il_info = (InfoLayout) findViewById(R.id.il_info);
        rv_recharge = (RecyclerView) findViewById(R.id.rv_recharge);
        btn_recharge = (Button) findViewById(R.id.btn_recharge);

        btn_recharge.setOnClickListener(this);
        money = new int[]{1, 5, 10, 20, 50, 100};
        for (int i = 0; i < money.length; i++) {
            list.add(money[i] + "元");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recharge:
                index = rechargeAdapter.getPositionIndex();
                Toast.makeText(context, money[index] + "元", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
