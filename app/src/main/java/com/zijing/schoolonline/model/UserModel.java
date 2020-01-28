package com.zijing.schoolonline.model;

import com.zijing.schoolonline.callback.RegisterCallBack;
import com.zijing.schoolonline.callback.RoomCallBack;
import com.zijing.schoolonline.callback.UCallBack;

public interface UserModel {
    void userLoginData(String phone, String password, final UCallBack uCallBack);

    void userRegisterData(String phone, String pwd, final RegisterCallBack registerCallBack);

    void userFindData(String phone, String pwd, final RegisterCallBack registerCallBack);

    void userLogOutData(String phone, final RegisterCallBack registerCallBack);

    void userGetUser(String phone, final RegisterCallBack registerCallBack);

    void airRechargeData(int money, final RegisterCallBack registerCallBack);

    void getRoomAreaData(final RoomCallBack roomCallBack);

    void getRoomDoorplateData(String area, final RoomCallBack roomCallBack);

    void bindingRoomData(Long userId, String area, String doorplate, final RoomCallBack roomCallBack);
}
