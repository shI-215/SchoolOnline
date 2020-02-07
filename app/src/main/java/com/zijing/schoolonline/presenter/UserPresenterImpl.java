package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.MyView;

public class UserPresenterImpl implements UserPresenter, MyCallback<Object> {
    private MyView myView;
    private UserModel userModel;

    public UserPresenterImpl(MyView myView) {
        this.myView = myView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void userLogin(String phone, String password) {
        userModel.userLoginData(phone, password, this);
    }

    @Override
    public void userRegister(String phone, String pwd) {
        userModel.userRegisterData(phone, pwd, this);
    }

    @Override
    public void userFind(String phone, String pwd) {
        userModel.userFindData(phone, pwd, this);
    }

    @Override
    public void userLogOut() {
        userModel.userLogOutData(this);
    }

    @Override
    public void onSuccess(Object object) {
        if (myView != null) {
            myView.onSuccess(object);
        }
    }

    @Override
    public void onFailed(Object object) {
        if (myView != null) {
            myView.onFailed(object);
        }
    }

    @Override
    public void onDestroy() {
        myView = null;
    }
}
