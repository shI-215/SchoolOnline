package com.zijing.schoolonline.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.adapter.RecordAdapter;
import com.zijing.schoolonline.bean.Record;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private List<Record> recordList = new ArrayList<>();
    private int recordType;

    private RecyclerView rv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recordType = getIntent().getIntExtra("recordType", 0);
        if (recordType == 1) {
            setTitle(ApplicationParam.AIR_RECORD_VALUE);
        } else if (recordType == 2) {
            setTitle(ApplicationParam.ELECT_RECORD_VALUE);
        } else if (recordType == 3) {
            setTitle(ApplicationParam.WATER_RECORD_VALUE);
        }
        initView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_record.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recordList);
        rv_record.setAdapter(adapter);
    }

    private void initView() {
        rv_record = (RecyclerView) findViewById(R.id.rv_record);

        for (int i = 1; i <= 20; i++) {
            Record record = new Record();
            record.setRid(i);
            record.setAccount("紫荆科技");
            record.setAmount(i + 1.0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            record.setTime(simpleDateFormat.format(date) + "");
            recordList.add(record);
        }
    }
}
