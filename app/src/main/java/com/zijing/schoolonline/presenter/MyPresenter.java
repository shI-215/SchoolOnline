package com.zijing.schoolonline.presenter;

public interface MyPresenter {

    void onDestroy();

    void getAirInfo(int roomId);

    void getElectInfo(int roomId);

    void getWaterInfo(int waterId);

    void airRecharge(int airId, int money);

    void electRecharge(Integer electId, int money);

    void waterRecharge(Integer waterId, int money);

    void getUserAllRecharge(int userId);
}
