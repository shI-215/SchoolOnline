package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.MainCallback;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.MainListening;

public class MainPresenterImpl implements MainPresenter, MainCallback<Object> {
    private MainListening mainListening;
    private UserModel userModel;

    public MainPresenterImpl(MainListening mainListening) {
        this.mainListening = mainListening;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void getUserInfo() {
        userModel.getUserInfoData(this);
    }

    @Override
    public void onSuccess(Object object) {
        if (mainListening != null) {
            mainListening.onSuccess(object);
        }
    }

    @Override
    public void onFailed(Object object) {
        if (mainListening != null) {
            mainListening.onFailed(object);
        }
    }

    @Override
    public void onError(Object object) {
        if (mainListening != null) {
            mainListening.onError(object);
        }
    }

    @Override
    public void onDestroy() {
        mainListening = null;
    }
}
