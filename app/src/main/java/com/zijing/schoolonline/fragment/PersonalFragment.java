package com.zijing.schoolonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zijing.schoolonline.MainActivity;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.activity.LoginActivity;
import com.zijing.schoolonline.activity.NextActivity;
import com.zijing.schoolonline.layout.ClickLayout;
import com.zijing.schoolonline.util.VersionCodeUtil;

public class PersonalFragment extends Fragment implements View.OnClickListener {

    private String roomInfo = "";
//    private String roomInfo = "一栋-101";

    private ImageView iv_user_image;
    private TextView tv_user_name;
    private TextView tv_user_signature;
    private ClickLayout cl_phone;
    private ClickLayout cl_room;
    private ClickLayout cl_version;
    private Button btn_unlogin;
    private ClickLayout cl_version_update;

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
        cl_room = (ClickLayout) getActivity().findViewById(R.id.cl_room);
        cl_version = (ClickLayout) getActivity().findViewById(R.id.cl_version);
        cl_version_update = (ClickLayout) getActivity().findViewById(R.id.cl_version_update);
        btn_unlogin = (Button) getActivity().findViewById(R.id.btn_unlogin);

        cl_phone.setText("电话", "12345678900", "", 0, true);
        cl_phone.setOnClickListener(this);
        if (TextUtils.isEmpty(roomInfo)) {
            cl_room.setText("宿舍", "未绑定宿舍", "", 1, true);
            cl_room.setClickable(true);
        } else {
            cl_room.setText("宿舍", roomInfo, "", 0, false);
            cl_room.setClickable(false);
        }
        String versionName = VersionCodeUtil.getVerName(getContext());
        cl_version.setText("版本", versionName, "", 0, false);
        cl_version_update.setText("检查更新", "", "", 0, false);
        btn_unlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_phone:
                Intent intent = new Intent();
                intent.setClass(getContext(), NextActivity.class);
                intent.putExtra("updatePhone", 1);
                startActivity(intent);
                break;
            case R.id.btn_unlogin:
                startActivity(new Intent(getContext(), LoginActivity.class));
                MainActivity.compatActivity.finish();
                break;
        }
    }
}
