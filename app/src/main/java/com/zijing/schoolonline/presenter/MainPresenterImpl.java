package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.MainCallback;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.MainView;

public class MainPresenterImpl implements MainPresenter, MainCallback<Object> {
    private MainView mainView;
    private UserModel userModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void getUserInfo() {
        userModel.getUserInfoData(this);
    }

    @Override
    public void onSuccess(Object object) {
        if (mainView != null) {
            mainView.onSuccess(object);
        }
    }

    @Override
    public void onFailed(Object object) {
        if (mainView != null) {
            mainView.onFailed(object);
        }
    }

    @Override
    public void onError(Object object) {
        if (mainView != null) {
            mainView.onError(object);
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }
}
