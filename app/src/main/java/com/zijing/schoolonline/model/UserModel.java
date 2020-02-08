package com.zijing.schoolonline.model;

import com.zijing.schoolonline.callback.MainCallback;
import com.zijing.schoolonline.callback.MyCallback;

public interface UserModel {
    void userLoginData(String phone, String password, final MyCallback myCallback);

    void userRegisterData(String phone, String pwd, final MyCallback myCallback);

    void userFindData(String phone, String pwd, final MyCallback myCallback);

    void getUserInfoData(final MainCallback mainCallback);

    void userLogOutData(final MyCallback myCallback);

    void alterPhoneData(String phone, final MyCallback myCallback);
}
