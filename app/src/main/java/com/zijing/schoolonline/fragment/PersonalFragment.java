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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.MainActivity;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.activity.LoginActivity;
import com.zijing.schoolonline.activity.NextActivity;
import com.zijing.schoolonline.activity.RoomActivity;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.layout.ClickLayout;
import com.zijing.schoolonline.presenter.RegisterPresenter;
import com.zijing.schoolonline.presenter.RegisterPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.util.VersionCodeUtil;
import com.zijing.schoolonline.view.RegisterView;

public class PersonalFragment extends Fragment implements View.OnClickListener, RegisterView {

    private RegisterPresenter registerPresenter;

    private String phone;

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
        registerPresenter = new RegisterPresenterImpl(this);
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

        tv_user_name.setText((String) SharedPreferencesUtil.get(getActivity(), "userName", "紫荆科技"));
        phone = (String) SharedPreferencesUtil.get(getActivity(), "userPhone", "12345678900");
        cl_phone.setText("电话", phone, "", 0, true);
        cl_phone.setOnClickListener(this);
        tv_user_signature.setText((String) SharedPreferencesUtil.get(getActivity(), "userAutograph", "走自己的路，让别人去说吧。"));
        if (TextUtils.isEmpty(ApplicationParam.ROOM_INFORMATION)) {
            cl_room.setText("宿舍", "宿舍认证", "", 1, true);
            cl_room.setEnabled(true);
        } else {
            cl_room.setText("宿舍", ApplicationParam.ROOM_INFORMATION, "", 0, false);
            cl_room.setEnabled(false);
        }
        String versionName = VersionCodeUtil.getVerName(getContext());
        cl_version.setText("版本", versionName, "", 0, false);
        cl_version_update.setText("检查更新", "", "", 0, false);

        cl_room.setOnClickListener(this);
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
            case R.id.cl_room:
                startActivity(new Intent(getContext(), RoomActivity.class));
                break;
            case R.id.btn_unlogin:
                registerPresenter.userLogOut(phone);
                break;
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "退出登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferencesUtil.clear(getActivity());
        startActivity(new Intent(getContext(), LoginActivity.class));
        MainActivity.compatActivity.finish();
    }

    @Override
    public void onFailed(Message message) {

    }
}
