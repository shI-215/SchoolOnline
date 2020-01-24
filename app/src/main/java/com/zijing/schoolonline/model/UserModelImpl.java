package com.zijing.schoolonline.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.callback.UCallBack;

import okhttp3.Call;
import okhttp3.MediaType;

public class UserModelImpl implements UserModel {
    private Message message;

    @Override
    public void userLoginData(String phone, String password, final UCallBack uCallBack) {
        User user = new User();
        user.setUserPhone(phone);
        user.setUserPassword(password);
        Log.v("userLoginData: user", user.toString());
        OkHttpUtils.postString().url(ApplicationParam.USER_LOGIN_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MyCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                uCallBack.onFailed();
            }

            @Override
            public void onResponse(User user1, int id) {
                uCallBack.onSuccess(user1);
            }
        });
    }
}
