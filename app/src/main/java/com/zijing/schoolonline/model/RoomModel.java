package com.zijing.schoolonline.model;


import com.zijing.schoolonline.callback.ListCallBack;
import com.zijing.schoolonline.callback.RoomCallBack;

public interface RoomModel {

    void getRoomAreaData(final ListCallBack listCallBack);

    void getRoomDoorplateData(String area, final ListCallBack listCallBack);

    void bindingRoomData(Long userId, String area, String doorplate, final ListCallBack listCallBack);

    void getRoomInfo(int roomId, final RoomCallBack roomCallBack);
}
