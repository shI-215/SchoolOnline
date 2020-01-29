package com.zijing.schoolonline.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.Room;
import com.zijing.schoolonline.callback.ListCallBack;
import com.zijing.schoolonline.callback.MessageCallback;
import com.zijing.schoolonline.callback.RoomCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class RoomModelImpl implements RoomModel {

    @Override
    public void getRoomAreaData(final ListCallBack listCallBack) {
        OkHttpUtils.get().url(ApplicationParam.ROOM_GETAREA_API).build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        Type type = new TypeToken<List<String>>() {
                        }.getType();
                        List<Object> list = new Gson().fromJson(message.getData(), type);
                        listCallBack.onSuccess(list);
                    }
                });
    }

    @Override
    public void getRoomDoorplateData(String area, final ListCallBack listCallBack) {
        OkHttpUtils.get().url(ApplicationParam.ROOM_GETDOORPLATE_API)
                .addParams("roomArea", area)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        Type type = new TypeToken<List<Integer>>() {
                        }.getType();
                        List<Object> list = new Gson().fromJson(message.getData(), type);
                        listCallBack.onSuccess(list);
                    }
                });
    }

    @Override
    public void bindingRoomData(Long userId, String area, String doorplate, final ListCallBack listCallBack) {
        OkHttpUtils.get().url(ApplicationParam.USER_BINDINGROOM_API)
                .addParams("userId", userId + "")
                .addParams("roomArea", area)
                .addParams("roomDoorplate", doorplate)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == 0) {
                            List<Object> list = new ArrayList<>();
                            listCallBack.onSuccess(list);
                        } else {
                            listCallBack.onFailed();
                        }
                    }
                });
    }

    @Override
    public void getRoomInfo(int roomId, final RoomCallBack roomCallBack) {
        Log.v("userModel", roomId + "");
        OkHttpUtils.get().url(ApplicationParam.ROOM_GETROOMINFO_API)
                .addParams("roomId", roomId + "")
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        roomCallBack.onFailed();
                    }

                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == 0) {
                            Log.v("room", message.getData());
                            Room room = new Gson().fromJson(message.getData(), Room.class);
                            Log.v("room", room.toString());
                            roomCallBack.onSuccess(room);
                        } else {
                            roomCallBack.onFailed();
                        }
                    }
                });
    }
}
