package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.UCallBack;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.view.UserView;

public class UserPresenter {
    private UserView userView;
    private UserModel userModel;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        userModel = new UserModel();
    }

    //登录
    public void userLogin(String phone, String password) {
        userModel.userLoginData(phone, password, new UCallBack() {
            @Override
            public void onSuccess(User user) {
                userView.showSuccess(user);
            }

            @Override
            public void onFailed() {
                userView.showFaile();
            }
        });
    }

    //找回密码
    public void userFind(String phone, String code) {
        userModel.userFindData(phone, code, new UCallBack() {
            @Override
            public void onSuccess(User user) {
                userView.showSuccess(user);
            }

            @Override
            public void onFailed() {
                userView.showFaile();
            }
        });
    }
}
