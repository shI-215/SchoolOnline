package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.callback.RegisterCallBack;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterCallBack {
    private RegisterView registerView;
    private UserModel userModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void userRegister(String phone, String pwd) {
        userModel.userRegisterData(phone, pwd, this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }

    @Override
    public void userFind(String phone, String pwd) {
        userModel.userFindData(phone, pwd, this);
    }

    @Override
    public void userLogOut(String phone) {
        userModel.userLogOutData(phone, this);
    }

    @Override
    public void userGetUser(String phone) {
        userModel.userGetUser(phone, this);
    }

    @Override
    public void airRecharge(int money) {
        userModel.airRechargeData(money, this);
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.onSuccess();
        }
    }

    @Override
    public void onFailed(Message message) {
        if (registerView != null) {
            registerView.onFailed(message);
        }
    }

    @Override
    public void onError() {

    }
}
