package com.zijing.schoolonline.model;

import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.UCallBack;

import java.util.Random;

public class UserModel {

    boolean isSuccess = false;

    public void userLoginData(String phone, String password, UCallBack uCallBack) {
        if (phone.equals("13456789022") && password.equals("12345678900")) {
            isSuccess = true;
        }
        if (isSuccess) {
            User user = new User();
//            用户名默认为手机号
            user.setName(phone);
            user.setPassword(password);
            uCallBack.onSuccess(user);
        } else {
            uCallBack.onFailed();
        }
    }

    public void userFindData(String phone, String code, UCallBack uCallBack) {
        Random random = new Random();
        isSuccess = random.nextBoolean();
        if (isSuccess) {
            User user = new User();
            user.setPhone(phone);
            uCallBack.onSuccess(user);
        } else {
            uCallBack.onFailed();
        }
    }
}
