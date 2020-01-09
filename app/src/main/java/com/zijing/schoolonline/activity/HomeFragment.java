package com.zijing.schoolonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.layout.CardLayout;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private CardLayout cl_air;
    private CardLayout cl_elect;
    private CardLayout cl_water;
    private Toolbar toolBar;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBar);
    }

    private void initView() {
        cl_air = (CardLayout) getActivity().findViewById(R.id.cl_air);
        cl_elect = (CardLayout) getActivity().findViewById(R.id.cl_elect);
        cl_water = (CardLayout) getActivity().findViewById(R.id.cl_water);
        toolBar = (Toolbar) getActivity().findViewById(R.id.toolBar);

        cl_air.setCard("空调", R.drawable.air);
        cl_elect.setCard("电费", R.drawable.elect);
        cl_water.setCard("水费", R.drawable.water);

        cl_air.setOnClickListener(this);
        cl_elect.setOnClickListener(this);
        cl_water.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.cl_air:
                intent.putExtra("titleRecharge", 1);
                break;
            case R.id.cl_elect:
                intent.putExtra("titleRecharge", 2);
                break;
            case R.id.cl_water:
                intent.putExtra("titleRecharge", 3);
                break;
        }
        intent.setClass(getActivity(), RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.record_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.item_air_record:
                intent.putExtra("recordType", 1);
                break;
            case R.id.item_elect_record:
                intent.putExtra("recordType", 2);
                break;
            case R.id.item_water_record:
                intent.putExtra("recordType", 3);
                break;
        }
        intent.setClass(getContext(), RecordActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
