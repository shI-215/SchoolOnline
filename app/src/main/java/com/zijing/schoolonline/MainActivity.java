package com.zijing.schoolonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zijing.schoolonline.activity.LoginActivity;
import com.zijing.schoolonline.adapter.MainAdapter;
import com.zijing.schoolonline.fragment.HomeFragment;
import com.zijing.schoolonline.fragment.PersonalFragment;
import com.zijing.schoolonline.presenter.MainPresenter;
import com.zijing.schoolonline.presenter.MainPresenterImpl;
import com.zijing.schoolonline.util.ToastUtil;
import com.zijing.schoolonline.view.MainListening;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainListening {

    public static AppCompatActivity compatActivity;
    private List<Fragment> mFragments;
    private PersonalFragment personalFragment;
    private HomeFragment homeFragment;
    private MainPresenter mainPresenter;
    private Context context;

    private ViewPager vp_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compatActivity = this;
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.getUserInfo();
        context = this;
        initView();
    }

    private void initView() {
        vp_main_page = (ViewPager) findViewById(R.id.vp_main_page);

        mFragments = new ArrayList<>();
        personalFragment = new PersonalFragment();
        homeFragment = new HomeFragment();
        mFragments.add(personalFragment);
        mFragments.add(homeFragment);

        MainAdapter mainPageAdapter = new MainAdapter(getSupportFragmentManager(), mFragments);
        vp_main_page.setAdapter(mainPageAdapter);
        vp_main_page.setCurrentItem(1);
    }

    @Override
    public void onSuccess(Object object) {
        personalFragment.update();
        homeFragment.update();
        Log.v("get", object.toString());
    }

    @Override
    public void onFailed(Object object) {
        ToastUtil.s(object.toString());
    }

    @Override
    public void onError(Object object) {
        SharedPreferences preferences = context.getSharedPreferences(App.SP_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        finish();
        startActivity(new Intent(context, LoginActivity.class));
        ToastUtil.l(object.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
