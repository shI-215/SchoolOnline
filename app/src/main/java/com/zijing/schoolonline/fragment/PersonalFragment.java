package com.zijing.schoolonline.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zijing.schoolonline.layout.ClickLayout;
import com.zijing.schoolonline.presenter.UserPresenter;
import com.zijing.schoolonline.presenter.UserPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.util.VersionCodeUtil;
import com.zijing.schoolonline.view.MyView;

public class PersonalFragment extends Fragment implements View.OnClickListener, MyView {

    SharedPreferences preferences = ApplicationParam.myContext.getSharedPreferences(ApplicationParam.SP_NAME,
            ApplicationParam.myContext.MODE_PRIVATE);
    private UserPresenter userPresenter;

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
        userPresenter = new UserPresenterImpl(this);
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

        String name = preferences.getString("name", "");
        phone = preferences.getString("phone", "");
        String room = preferences.getString("room", "");
        tv_user_name.setText(name);
        cl_phone.setText("电话", phone, "", 0, true);
        cl_phone.setOnClickListener(this);
        tv_user_signature.setText((String) SharedPreferencesUtil.get(getActivity(), "userAutograph", "走自己的路，让别人去说吧。"));
        if (TextUtils.isEmpty(room)) {
            cl_room.setText("宿舍", "宿舍认证", "", 1, true);
            cl_room.setEnabled(true);
        } else {
            cl_room.setText("宿舍", room, "", 0, false);
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
                userPresenter.userLogOut();
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {
        Toast.makeText(getActivity(), "退出登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(getContext(), LoginActivity.class));
        MainActivity.compatActivity.finish();
    }

    @Override
    public void onFailed(Object object) {

    }
}
