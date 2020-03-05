package com.zijing.schoolonline.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.adapter.RecordAdapter;
import com.zijing.schoolonline.bean.Recharge;
import com.zijing.schoolonline.presenter.MyPresenter;
import com.zijing.schoolonline.presenter.MyPresenterImpl;
import com.zijing.schoolonline.util.ToastUtil;
import com.zijing.schoolonline.view.MyListening;

import java.lang.reflect.Type;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements MyListening {

    private List<Recharge> recharges;
    private MyPresenter myPresenter;
    private Context context;

    private RecyclerView rv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("充值记录");
        myPresenter = new MyPresenterImpl(this);
        new Thread() {
            @Override
            public void run() {
                super.run();
                myPresenter.getUserAllRecharge();
            }
        }.start();
        context = this;
        initView();
    }

    private void initView() {
        rv_record = (RecyclerView) findViewById(R.id.rv_record);
    }

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
        Type type = new TypeToken<List<Recharge>>() {
        }.getType();
        recharges = new Gson().fromJson(object.toString(), type);
        Log.v("rechargeActivity", recharges.toString());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_record.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recharges);
        rv_record.setAdapter(adapter);
        Log.v("rechargeActivity", recharges.toString());
    }

    @Override
    public void onFailed(Object object) {
        ToastUtil.l(object.toString());
    }
}
