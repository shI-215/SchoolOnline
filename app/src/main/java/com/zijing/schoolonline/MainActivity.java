package com.zijing.schoolonline;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zijing.schoolonline.activity.HomeFragment;
import com.zijing.schoolonline.activity.PersonalFragment;
import com.zijing.schoolonline.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AppCompatActivity compatActivity;
    private List<Fragment> mFragments;

    private ViewPager vp_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compatActivity = this;
        initView();
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
}
