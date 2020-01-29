package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.bean.User;
import com.zijing.schoolonline.callback.LoginCallBack;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.LoginView;

public class UserPresenterImpl implements UserPresenter, LoginCallBack {
    private LoginView loginView;
    private UserModel userModel;

    public UserPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void onSuccess(User user) {
        if (loginView != null) {
            loginView.onSuccess(user);
        }
    }

    @Override
    public void onFailed() {
        if (loginView != null) {
            loginView.onFailed();
        }
    }

    @Override
    public void userLogin(String phone, String password) {
        userModel.userLoginData(phone, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }
}
