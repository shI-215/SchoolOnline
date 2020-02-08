package com.zijing.schoolonline.presenter;

public interface UserPresenter {
    void userLogin(String phone, String password);

    void userRegister(String phone, String pwd);

    void userFind(String phone, String pwd);

    void userLogOut();

    void onDestroy();

    void alterPhone(String phone);
}
