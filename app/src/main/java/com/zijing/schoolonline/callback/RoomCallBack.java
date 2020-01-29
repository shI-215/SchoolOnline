package com.zijing.schoolonline.callback;

import com.zijing.schoolonline.bean.Room;

public interface RoomCallBack {
    void onSuccess(Room room);

    void onFailed();
}
