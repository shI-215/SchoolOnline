package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.MyCallback;
import com.zijing.schoolonline.model.MyModel;
import com.zijing.schoolonline.model.MyModelImpl;
import com.zijing.schoolonline.view.MyListening;

public class MyPresenterImpl implements MyPresenter, MyCallback<Object> {
    private MyListening myListening;
    private MyModel myModel;

    public MyPresenterImpl(MyListening myListening) {
        this.myListening = myListening;
        this.myModel = new MyModelImpl();
    }

    @Override
    public void getAirInfo(int roomId) {
        myModel.getAirInfoData(roomId, this);
    }

    @Override
    public void getElectInfo(int roomId) {
        myModel.getElectInfoData(roomId, this);
    }

    @Override
    public void getWaterInfo(int waterId) {
        myModel.getWaterInfoData(waterId, this);
    }

    @Override
    public void getUserAllRecharge() {
        myModel.getUserAllRechargeData(this);
    }

    @Override
    public void airRecharge(int airId, int money) {
        myModel.airRechargeData(airId, money, this);
    }

    @Override
    public void electRecharge(Integer electId, int money) {
        myModel.electRechargeData(electId, money, this);
    }

    @Override
    public void waterRecharge(Integer waterId, int money) {
        myModel.waterRechargeData(waterId, money, this);
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
