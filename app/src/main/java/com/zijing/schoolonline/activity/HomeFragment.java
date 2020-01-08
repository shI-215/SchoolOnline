package com.zijing.schoolonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.layout.CardLayout;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private CardLayout cl_air;
    private CardLayout cl_elect;
    private CardLayout cl_water;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        cl_air = (CardLayout) getActivity().findViewById(R.id.cl_air);
        cl_elect = (CardLayout) getActivity().findViewById(R.id.cl_elect);
        cl_water = (CardLayout) getActivity().findViewById(R.id.cl_water);

        cl_air.setCard("空调", R.drawable.air);
        cl_elect.setCard("电费", R.drawable.elect);
        cl_water.setCard("水费", R.drawable.water);

        cl_air.setOnClickListener(this);
        cl_elect.setOnClickListener(this);
        cl_water.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_air:
                startActivity(new Intent(getActivity(), RechargeActivity.class));
                break;
//            case R.id.cl_elect:
//                startActivity(new Intent(getActivity(), PersonalFragment.class));
//                break;
//            case R.id.cl_water:
//                startActivity(new Intent(getActivity(), PersonalFragment.class));
//                break;
        }
    }
}
