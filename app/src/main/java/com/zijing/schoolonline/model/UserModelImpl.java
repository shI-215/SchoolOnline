package com.zijing.schoolonline.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zijing.schoolonline.App;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.MainCallback;
import com.zijing.schoolonline.callback.MessageCallback;
import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.view.PictureListening;

import java.io.File;

import okhttp3.CookieJar;
import okhttp3.MediaType;

public class UserModelImpl implements UserModel {

    private SharedPreferences preferences = App.myContext.getSharedPreferences(App.SP_NAME, Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = preferences.edit();
    private User user = new User();

    @Override
    public void userLoginData(String phone, String pwd, final MyCallback myCallback) {
        OkHttpUtils.post().url(App.USER_LOGIN_API)
                .addParams("userPhone", phone)
                .addParams("userPassword", pwd)
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                } else {
                    myCallback.onFailed(message.getData());
                }
            }
        });
    }

    @Override
    public void userRegisterData(String phone, String pwd, final MyCallback myCallback) {
        user.setUserPhone(phone);
        user.setUserPassword(pwd);
        Log.v("userRegisterData: user", user.toString());
        OkHttpUtils.postString().url(App.USER_REGISTER_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_FAILED) {
                    myCallback.onFailed(message.getData());
                } else if (message.getStatus() == App.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                }
            }
        });
    }

    @Override
    public void userFindData(String phone, String pwd, final MyCallback myCallback) {
        user = new User();
        user.setUserPhone(phone);
        user.setUserPassword(pwd);
        Log.v("userRegisterData: user", user.toString());
        OkHttpUtils.postString().url(App.USER_FIND_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_FAILED) {
                    myCallback.onFailed(message.getData());
                } else if (message.getStatus() == App.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                }
            }
        });
    }

    @Override
    public void getUserInfoData(final MainCallback mainCallback) {
        OkHttpUtils.get().url(App.USER_GETUSER_API)
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_SUCCESS) {
                    User user = new Gson().fromJson(message.getData(), User.class);
                    Log.v("getUser", user.toString());
                    editor.putString("name", user.getUserName());
                    editor.putString("phone", user.getUserPhone());
                    editor.putString("autograph", user.getUserAutograph());
                    if (null != user.getUserPicture()) {
                        editor.putString("picture", user.getUserPicture());
                    } else {
                        editor.putString("picture", "");
                    }
                    if (null != user.getWater()) {
                        editor.putInt("waterId", user.getWater().getWaterId());
                        editor.putString("waterMoney", user.getWater().getWaterMoney() + "");
                    } else {
                        editor.putInt("waterId", 0);
                        editor.putString("waterMoney", "");
                    }
                    if (null != user.getRoom()) {
                        editor.putInt("roomId", user.getRoom().getRoomId());
                        editor.putString("room", user.getRoom().getRoomArea() + "-" + user.getRoom().getRoomDoorplate());
                    } else {
                        editor.putInt("roomId", 0);
                        editor.putString("room", "");
                    }
                    editor.commit();
                    mainCallback.onSuccess(message.getData());
                } else if (message.getStatus() == App.STATUS_FAILED) {
                    mainCallback.onFailed(message.getData());
                } else {
                    mainCallback.onError("登录失效，请重新登录");
                }
            }
        });
    }

    @Override
    public void userLogOutData(final MyCallback myCallback) {
        OkHttpUtils.get().url(App.USER_LOGOUT_API)
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_FAILED) {
                    myCallback.onFailed(message);
                } else if (message.getStatus() == App.STATUS_SUCCESS) {//清空session
                    CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
                    if (cookieJar instanceof CookieJarImpl) {
                        ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
                    }
                    myCallback.onSuccess(message.getData());
                }
            }
        });
    }

    @Override
    public void alterPhoneData(String phone, final MyCallback myCallback) {
        user.setUserPhone(phone);
        OkHttpUtils.postString().url(App.USER_ALTERPHONE_API)
                .content(new Gson().toJson(user))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == App.STATUS_FAILED) {
                    myCallback.onFailed(message.getData());
                } else if (message.getStatus() == App.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                }
            }
        });
    }

    @Override
    public void alterPictureData(File file, final PictureListening pictureListening) {
        OkHttpUtils.post()
                .addFile("image", file.getName(), file)//图片
                .url(App.USER_IMAGE_API)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == App.STATUS_FAILED) {
                            pictureListening.onFailed(message.getData());
                        } else if (message.getStatus() == App.STATUS_SUCCESS) {
                            pictureListening.onSuccess(message.getData());
                        }
                    }
                });
    }
}
