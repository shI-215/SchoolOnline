package com.zijing.schoolonline.presenter;

public interface UserPresenter {
    void userLogin(String phone, String password);

    void onDestroy();
}
