package com.zijing.schoolonline.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.MessageCallback;
import com.zijing.schoolonline.callback.RegisterCallBack;
import com.zijing.schoolonline.callback.LoginCallBack;
import com.zijing.schoolonline.callback.UserCallback;

import okhttp3.Call;
import okhttp3.MediaType;

public class UserModelImpl implements UserModel {
    private User user;

    @Override
    public void userLoginData(String phone, String password, final LoginCallBack loginCallBack) {
        user = new User();
        user.setUserPhone(phone);
        user.setUserPassword(password);
        Log.v("userLoginData: user", user.toString());
        OkHttpUtils.postString().url(ApplicationParam.USER_LOGIN_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new UserCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                loginCallBack.onFailed();
            }

            @Override
            public void onResponse(User user1, int id) {
                loginCallBack.onSuccess(user1);
            }
        });
    }

    @Override
    public void userRegisterData(String phone, String pwd, final RegisterCallBack registerCallBack) {
        user = new User();
        user.setUserPhone(phone);
        user.setUserPassword(pwd);
        Log.v("userRegisterData: user", user.toString());
        OkHttpUtils.postString().url(ApplicationParam.USER_REGISTER_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                registerCallBack.onError();
            }

            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == 500) {
                    registerCallBack.onFailed(message);
                } else if (message.getStatus() == 0) {
                    registerCallBack.onSuccess();
                }
            }
        });
    }

    @Override
    public void userFindData(String phone, String pwd, final RegisterCallBack registerCallBack) {
        user = new User();
        user.setUserPhone(phone);
        user.setUserPassword(pwd);
        Log.v("userRegisterData: user", user.toString());
        OkHttpUtils.postString().url(ApplicationParam.USER_FIND_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                registerCallBack.onError();
            }

            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == 500) {
                    registerCallBack.onFailed(message);
                } else if (message.getStatus() == 0) {
                    registerCallBack.onSuccess();
                }
            }
        });
    }

    @Override
    public void userLogOutData(String phone, final RegisterCallBack registerCallBack) {
        Log.v("phone::::::::::", phone);
        OkHttpUtils.post().url(ApplicationParam.USER_LOGOUT_API).addParams("userPhone", phone)
                .build().execute(new MessageCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                registerCallBack.onError();
            }

            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == 500) {
                    registerCallBack.onFailed(message);
                } else if (message.getStatus() == 0) {
                    registerCallBack.onSuccess();
                }
            }
        });
    }

    @Override
    public void userGetUser(String phone, final RegisterCallBack registerCallBack) {
        Log.v("userGetUser::::::::::", phone);
        OkHttpUtils.post().url(ApplicationParam.USER_GETUSER_API).addParams("userPhone", phone)
                .build().execute(new MessageCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                registerCallBack.onError();
            }

            @Override
            public void onResponse(Message message, int id) {
                Log.v("UserModelImpl::::::", message.toString());
                if (message.getStatus() == 500) {
                    registerCallBack.onFailed(message);
                } else if (message.getStatus() == 0) {
                    registerCallBack.onSuccess();
                } else if (message.getStatus() == 400) {
                    registerCallBack.onFailed(message);
                }
            }
        });
    }

    @Override
    public void airRechargeData(int roomId, int money, final RegisterCallBack registerCallBack) {
        OkHttpUtils.postString().url(ApplicationParam.AIR_RECHARGE_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                registerCallBack.onError();
            }

            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == 500) {
                    registerCallBack.onFailed(message);
                } else if (message.getStatus() == 0) {
                    registerCallBack.onSuccess();
                }
            }
        });
    }
}
