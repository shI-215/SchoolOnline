package com.zijing.schoolonline.model;

import com.zijing.schoolonline.callback.UCallBack;

public interface UserModel {
    void userLoginData(String phone, String password, final UCallBack uCallBack);
}
