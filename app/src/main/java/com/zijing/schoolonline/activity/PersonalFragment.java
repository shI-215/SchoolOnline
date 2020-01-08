package com.zijing.schoolonline.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.layout.ClickLayout;

public class PersonalFragment extends Fragment {

    private ImageView iv_user_image;
    private TextView tv_user_name;
    private TextView tv_user_signature;
    private ClickLayout cl_phone;
    private ClickLayout cl_birthday;
    private ClickLayout cl_room;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        iv_user_image = (ImageView) getActivity().findViewById(R.id.iv_user_image);
        tv_user_name = (TextView) getActivity().findViewById(R.id.tv_user_name);
        tv_user_signature = (TextView) getActivity().findViewById(R.id.tv_user_signature);
        cl_phone = (ClickLayout) getActivity().findViewById(R.id.cl_phone);
        cl_birthday = (ClickLayout) getActivity().findViewById(R.id.cl_birthday);
        cl_room = (ClickLayout) getActivity().findViewById(R.id.cl_room);

        cl_phone.setText("联系电话", "12345678900", true);
        cl_birthday.setText("出生日期", "2019-12-30", true);
        cl_room.setText("宿舍信息", "一栋-101", false);
    }
}
