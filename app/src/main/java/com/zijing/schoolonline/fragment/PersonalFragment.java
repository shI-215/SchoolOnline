package com.zijing.schoolonline.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ogh.library.photos.IPhotoResult;
import com.ogh.library.photos.TakePhotoDialog;
import com.zijing.schoolonline.App;
import com.zijing.schoolonline.MainActivity;
import com.zijing.schoolonline.R;
import com.zijing.schoolonline.UpdateDate;
import com.zijing.schoolonline.activity.LoginActivity;
import com.zijing.schoolonline.activity.NextActivity;
import com.zijing.schoolonline.activity.RoomActivity;
import com.zijing.schoolonline.layout.ClickLayout;
import com.zijing.schoolonline.presenter.PicturePresenter;
import com.zijing.schoolonline.presenter.PicturePresenterImpl;
import com.zijing.schoolonline.presenter.UserPresenter;
import com.zijing.schoolonline.presenter.UserPresenterImpl;
import com.zijing.schoolonline.util.SharedPreferencesUtil;
import com.zijing.schoolonline.util.ToastUtil;
import com.zijing.schoolonline.util.VersionCodeUtil;
import com.zijing.schoolonline.view.MyListening;
import com.zijing.schoolonline.view.PictureListening;

import java.io.File;

public class PersonalFragment extends Fragment implements View.OnClickListener, MyListening, PictureListening, UpdateDate {

    private SharedPreferences preferences = App.myContext.getSharedPreferences(App.SP_NAME,
            App.myContext.MODE_PRIVATE);
    private UserPresenter userPresenter;
    private PicturePresenter picturePresenter;
    private TakePhotoDialog takePhotoDialog;
    private String phone;

    private RoundedImageView iv_user_image;
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
        picturePresenter = new PicturePresenterImpl(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        iv_user_image = (RoundedImageView) getActivity().findViewById(R.id.iv_user_image);
        iv_user_image.setOnClickListener(this);
        tv_user_name = (TextView) getActivity().findViewById(R.id.tv_user_name);
        tv_user_signature = (TextView) getActivity().findViewById(R.id.tv_user_signature);
        cl_phone = (ClickLayout) getActivity().findViewById(R.id.cl_phone);
        cl_room = (ClickLayout) getActivity().findViewById(R.id.cl_room);
        cl_room.setOnClickListener(this);
        cl_version = (ClickLayout) getActivity().findViewById(R.id.cl_version);
        cl_version_update = (ClickLayout) getActivity().findViewById(R.id.cl_version_update);
        btn_unlogin = (Button) getActivity().findViewById(R.id.btn_unlogin);
        btn_unlogin.setOnClickListener(this);

        String picture = preferences.getString("picture", "");
        Log.v("picture", picture);
        if (!TextUtils.isEmpty(picture)) {
            Glide.with(getContext()).load(App.SCHOOL_URL + picture)
                    .error(R.drawable.picture).into(iv_user_image);
        }
        String name = preferences.getString("name", "");
        phone = preferences.getString("phone", "");
        String room = preferences.getString("room", "");
        tv_user_name.setText(name);
        cl_phone.setText(R.drawable.ic_phone, "电话", phone, "", 0, true);
        cl_phone.setOnClickListener(this);
        tv_user_signature.setText((String) SharedPreferencesUtil.get(getActivity(), "userAutograph", "走自己的路，让别人去说吧。"));
        if (TextUtils.isEmpty(room)) {
            cl_room.setText(R.drawable.ic_room, "宿舍", "宿舍认证", "", 1, true);
            cl_room.setEnabled(true);
        } else {
            cl_room.setText(R.drawable.ic_room, "宿舍", room, "", 0, false);
            cl_room.setEnabled(false);
        }
        String versionName = VersionCodeUtil.getVerName(getContext());
        cl_version.setText(R.drawable.ic_version, "版本", versionName, "", 0, false);
        cl_version_update.setText(R.drawable.ic_update, "检查更新", "", "", 0, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_image:
                takePhotoDialog = new TakePhotoDialog(this);
                takePhotoDialog.isCrop(true);//裁剪
                takePhotoDialog.isCompress(true);//压缩
                takePhotoDialog.takePhoto();
                break;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhotoDialog.onPhotoResult(requestCode, resultCode, data, new IPhotoResult() {
            @Override
            public void onResult(File file, String path) {
                picturePresenter.alterPicture(file);
            }
        });
    }

    @Override
    public void onSuccess(String string) {
        String path = App.SCHOOL_URL + string;
        Glide.with(getContext()).load(path)
                .error(R.drawable.picture)
                .into(iv_user_image);
    }

    @Override
    public void onFailed(String string) {
        ToastUtil.l(string);
    }

    @Override
    public void update() {
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userPresenter.onDestroy();
        picturePresenter.onDestroy();
    }
}
