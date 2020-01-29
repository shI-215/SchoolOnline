package com.zijing.schoolonline;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zijing.schoolonline.adapter.MainAdapter;
import com.zijing.schoolonline.bean.Room;
import com.zijing.schoolonline.fragment.HomeFragment;
import com.zijing.schoolonline.fragment.PersonalFragment;
import com.zijing.schoolonline.presenter.RoomPresenter;
import com.zijing.schoolonline.presenter.RoomPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.view.RoomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RoomView {

    public static AppCompatActivity compatActivity;
    private List<Fragment> mFragments;
    private Context context;
    private RoomPresenter roomPresenter;

    private ViewPager vp_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compatActivity = this;
        context = this;
        roomPresenter = new RoomPresenterImpl(this);
        int roomId = (int) SharedPreferencesUtil.get(context, "roomId", 0);
        Log.v("roomId", roomId + "");
        if (roomId != 0) {
            getRoomInfo(roomId);
        }
        initView();
    }

    private void getRoomInfo(int roomId) {
        roomPresenter.getRoomInfo(roomId);
    }

    private void initView() {
        vp_main_page = (ViewPager) findViewById(R.id.vp_main_page);

        mFragments = new ArrayList<>();
        PersonalFragment personalFragment = new PersonalFragment();
        HomeFragment homeFragment = new HomeFragment();
        mFragments.add(personalFragment);
        mFragments.add(homeFragment);

        MainAdapter mainPageAdapter = new MainAdapter(getSupportFragmentManager(), mFragments);
        vp_main_page.setAdapter(mainPageAdapter);
        vp_main_page.setCurrentItem(1);
    }

    @Override
    public void onSuccess(Room room) {
        ApplicationParam.ROOM_INFORMATION = room.getRoomArea() + "-" + room.getRoomDoorplate();
    }

    @Override
    public void onFailed() {
        Toast.makeText(context, "获取信息失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        roomPresenter.onDestroy();
    }
}
