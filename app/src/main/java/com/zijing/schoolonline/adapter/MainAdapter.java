package com.zijing.schoolonline.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;

    public MainAdapter(FragmentManager fragmentManager, List<Fragment> mFragments) {
        super(fragmentManager);
        this.fragments = mFragments;
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
