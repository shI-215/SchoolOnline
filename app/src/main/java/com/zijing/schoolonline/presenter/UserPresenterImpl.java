package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.MyListening;

public class UserPresenterImpl implements UserPresenter, MyCallback<Object> {
    private MyListening myListening;
    private UserModel userModel;

    public UserPresenterImpl(MyListening myListening) {
        this.myListening = myListening;
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
    public void alterPhone(String phone) {
        userModel.alterPhoneData(phone, this);
    }

    @Override
    public void onSuccess(Object object) {
        if (myListening != null) {
            myListening.onSuccess(object);
        }
    }

    @Override
    public void onFailed(Object object) {
        if (myListening != null) {
            myListening.onFailed(object);
        }
    }

    @Override
    public void onDestroy() {
        myListening = null;
    }
}
