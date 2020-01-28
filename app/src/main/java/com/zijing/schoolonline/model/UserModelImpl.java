package com.zijing.schoolonline.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.MessageCallback;
import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.callback.RegisterCallBack;
import com.zijing.schoolonline.callback.RoomCallBack;
import com.zijing.schoolonline.callback.UCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class UserModelImpl implements UserModel {
    private User user;

    @Override
    public void userLoginData(String phone, String password, final UCallBack uCallBack) {
        user = new User();
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
    public void airRechargeData(int money, final RegisterCallBack registerCallBack) {
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

    @Override
    public void getRoomAreaData(final RoomCallBack roomCallBack) {
        OkHttpUtils.get().url(ApplicationParam.ROOM_GETAREA_API).build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        roomCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        Type type = new TypeToken<List<String>>() {
                        }.getType();
                        List<Object> list = new Gson().fromJson(message.getData(), type);
                        roomCallBack.onSuccess(list);
                    }
                });
    }

    @Override
    public void getRoomDoorplateData(String area, final RoomCallBack roomCallBack) {
        OkHttpUtils.get().url(ApplicationParam.ROOM_GETDOORPLATE_API)
                .addParams("roomArea", area)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        roomCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        Type type = new TypeToken<List<Integer>>() {
                        }.getType();
                        List<Object> list = new Gson().fromJson(message.getData(), type);
                        roomCallBack.onSuccess(list);
                    }
                });
    }

    @Override
    public void bindingRoomData(Long userId, String area, String doorplate, final RoomCallBack roomCallBack) {
        OkHttpUtils.get().url(ApplicationParam.USER_BINDINGROOM_API)
                .addParams("userId", userId + "")
                .addParams("roomArea", area)
                .addParams("roomDoorplate", doorplate)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        roomCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == 0) {
                            List<Object> list = new ArrayList<>();
                            roomCallBack.onSuccess(list);
                        } else {
                            roomCallBack.onFailed();
                        }
                    }
                });
    }
}
