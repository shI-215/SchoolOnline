package com.zijing.schoolonline.view;

import com.zijing.schoolonline.bean.Room;

public interface RoomListening {
    void onSuccess(Room room);

    void onFailed();
}
