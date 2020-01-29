package com.zijing.schoolonline.model;


import com.zijing.schoolonline.callback.MyCallback;

public interface MyModel {

    void getAirInfoData(int roomId, final MyCallback myCallback);

    void getElectInfoData(int roomId, final MyCallback myCallback);

    void getWaterInfoData(int waterId, final MyCallback myCallback);

    void airRechargeData(int airId, int money, final MyCallback myCallback);

    void electRechargeData(Integer electId, int money, final MyCallback myCallback);

    void waterRechargeData(Integer waterId, int money, final MyCallback myCallback);

    void getUserAllRechargeData(int userId, final MyCallback myCallback);
}
