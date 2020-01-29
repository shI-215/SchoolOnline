package com.zijing.schoolonline.presenter;

public interface RegisterPresenter {
    void userRegister(String phone, String pwd);

    void onDestroy();

    void userFind(String phone, String pwd);

    void userLogOut(String phone);

    void userGetUser(String phone);

    void airRecharge(int roomId, int index);
}
