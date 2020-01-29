package com.zijing.schoolonline.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.adapter.RechargeAdapter;
import com.zijing.schoolonline.bean.Air;
import com.zijing.schoolonline.bean.Elect;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.Water;
import com.zijing.schoolonline.presenter.MyPresenter;
import com.zijing.schoolonline.presenter.MyPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener, MyView {

    private MyPresenter myPresenter;
    private Context context;
    private RechargeAdapter rechargeAdapter;

    private List<String> list = new ArrayList<>();

    private int index;
    private int[] money;
    private int titleRecharge;
    private int roomId;
    private int waterId;
    private boolean isRecharge = false;

    private Air air;
    private Water water;
    private Elect elect;

    private RecyclerView rv_recharge;
    private Button btn_recharge;
    private TextView tv_account;
    private TextView tv_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titleRecharge = getIntent().getIntExtra("titleRecharge", 0);
        myPresenter = new MyPresenterImpl(this);
        if (titleRecharge == 1) {
            setTitle(ApplicationParam.AIR_RECHARGE_VALUE);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    roomId = (int) SharedPreferencesUtil.get(context, "roomId", 0);
                    myPresenter.getAirInfo(roomId);
                }
            }.start();
        } else if (titleRecharge == 2) {
            setTitle(ApplicationParam.ELECT_RECHARGE_VALUE);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    roomId = (int) SharedPreferencesUtil.get(context, "roomId", 0);
                    myPresenter.getElectInfo(roomId);
                }
            }.start();
        } else if (titleRecharge == 3) {
            setTitle(ApplicationParam.WATER_RECHARGE_VALUE);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    waterId = (int) SharedPreferencesUtil.get(context, "waterId", 0);
                    Log.v("waterId", waterId + "");
                    myPresenter.getWaterInfo(waterId);
                }
            }.start();
        }
        initView();
        context = this;
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv_recharge.setLayoutManager(layoutManager);
        rechargeAdapter = new RechargeAdapter(list);
        rv_recharge.setAdapter(rechargeAdapter);
    }

    private void initView() {
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_balance = (TextView) findViewById(R.id.tv_balance);
        rv_recharge = (RecyclerView) findViewById(R.id.rv_recharge);
        btn_recharge = (Button) findViewById(R.id.btn_recharge);

        if (titleRecharge == 1 || titleRecharge == 2) {
            tv_account.setText(ApplicationParam.ROOM_INFORMATION);
        } else if (titleRecharge == 3) {
            tv_account.setText(ApplicationParam.USER_PHONE);
        }

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
                isRecharge = true;
                if (titleRecharge == 1) {//空调充值
                    Log.v("air", air.getAirId() + " " + money[index]);
                    myPresenter.airRecharge(air.getAirId(), money[index]);
                } else if (titleRecharge == 2) {//电费充值
                    Log.v("elect", elect.getElectId() + " " + money[index]);
                    myPresenter.electRecharge(elect.getElectId(), money[index]);
                } else if (titleRecharge == 3) {//水费充值
                    Log.v("water", water.getWaterId() + " " + money[index]);
                    myPresenter.waterRecharge(water.getWaterId(), money[index]);
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(Object object) {
//        startActivity(new Intent(context, MainActivity.class));
//        finish();
//        Toast.makeText(context, "充值成功", Toast.LENGTH_LONG).show();
        if (isRecharge == false) {
            if (titleRecharge == 1) {
                air = new Air();
                air = (Air) object;
                Log.v("air::::", air.toString());
                tv_balance.setText(air.getAirMoney() + "元");
            } else if (titleRecharge == 2) {
                elect = (Elect) object;
                Log.v("elect::::", elect.toString());
                tv_balance.setText(elect.getElectMoney() + "元");
            } else if (titleRecharge == 3) {
                water = (Water) object;
                Log.v("water::::", water.toString());
                tv_balance.setText(water.getWaterMoney() + "元");
            }
        } else {
            Message message = (Message) object;
            Toast.makeText(context, message.getMsg(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFailed(Object object) {
        if (isRecharge == false) {
            Toast.makeText(context, "查询失败", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "充值失败", Toast.LENGTH_LONG).show();
        }
    }
}
