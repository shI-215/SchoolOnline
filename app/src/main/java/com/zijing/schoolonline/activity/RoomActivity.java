package com.zijing.schoolonline.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.presenter.ListPresenter;
import com.zijing.schoolonline.presenter.ListPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.view.ListView;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener, ListView {

    private ListPresenter listPresenter;
    private Context context;

    private Long userId;
    private int SHOW_TYPE = 0;
    private String SHOW_TITLE = "";
    private String area = "";

    private EditText edt_area;
    private EditText edt_doorplate;
    private Button btn_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        initView();
        setTitle("宿舍认证");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        listPresenter = new ListPresenterImpl(this);
        context = this;
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

    private void initView() {
        edt_area = (EditText) findViewById(R.id.edt_area);
        edt_doorplate = (EditText) findViewById(R.id.edt_doorplate);
        btn_room = (Button) findViewById(R.id.btn_room);

        edt_area.setOnClickListener(this);
        edt_doorplate.setOnClickListener(this);
        btn_room.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_area:
                SHOW_TYPE = 1;
                SHOW_TITLE = "区域选择";
                listPresenter.getRoomArea();
                break;
            case R.id.edt_doorplate:
                if (TextUtils.isEmpty(area)) {
                    Toast.makeText(context, "请先选择区域", Toast.LENGTH_LONG).show();
                } else {
                    SHOW_TYPE = 2;
                    SHOW_TITLE = "门牌号选择";
                    listPresenter.getRoomDoorplate(area);
                }
                break;
            case R.id.btn_room:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String area = edt_area.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            Toast.makeText(this, "区域不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String doorplate = edt_doorplate.getText().toString().trim();
        if (TextUtils.isEmpty(doorplate)) {
            Toast.makeText(this, "门牌号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        userId = (Long) SharedPreferencesUtil.get(context, "userId", 0L);
        SHOW_TYPE = 3;
        listPresenter.bindingRoom(userId, area, doorplate);
    }

    @Override
    public void onSuccess(List<Object> list) {
        if (SHOW_TYPE == 3) {
            Toast.makeText(context, "认证成功", Toast.LENGTH_LONG).show();
            finish();
        } else {
            String[] strings = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strings[i] = list.get(i).toString();
            }
            show(strings);
        }
    }

    private void show(final String[] strings) {
        new AlertDialog.Builder(context).setTitle(SHOW_TITLE)
                .setSingleChoiceItems(strings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (SHOW_TYPE == 1) {
                            area = strings[which];
                            edt_area.setText(strings[which]);
                        } else if (SHOW_TYPE == 2) {
                            edt_doorplate.setText(strings[which]);
                        }
                    }
                }).setCancelable(false)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    @Override
    public void onFailed() {
        Toast.makeText(context, "认证失败", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listPresenter.onDestroy();
    }
}
