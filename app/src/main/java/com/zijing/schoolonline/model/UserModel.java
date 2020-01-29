package com.zijing.schoolonline.model;

import com.zijing.schoolonline.callback.RegisterCallBack;
import com.zijing.schoolonline.callback.LoginCallBack;

public interface UserModel {
    void userLoginData(String phone, String password, final LoginCallBack loginCallBack);

    void userRegisterData(String phone, String pwd, final RegisterCallBack registerCallBack);

    void userFindData(String phone, String pwd, final RegisterCallBack registerCallBack);

    void userLogOutData(String phone, final RegisterCallBack registerCallBack);

    void userGetUser(String phone, final RegisterCallBack registerCallBack);

    void airRechargeData(int roomId, int money, final RegisterCallBack registerCallBack);
}
