package com.zijing.schoolonline.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zijing.schoolonline.ApplicationParam;
import com.zijing.schoolonline.bean.Air;
import com.zijing.schoolonline.bean.Elect;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.Water;
import com.zijing.schoolonline.callback.MessageCallback;
import com.zijing.schoolonline.callback.MyCallback;

import okhttp3.MediaType;

public class MyModelImpl implements MyModel {

    @Override
    public void getAirInfoData(int roomId, final MyCallback myCallback) {
        OkHttpUtils.get().url(ApplicationParam.AIR_GETMONEY_API)
                .addParams("roomId", roomId + "").build()
                .execute(new MessageCallback() {
                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                            myCallback.onSuccess(new Gson().fromJson(message.getData(), Air.class));
                        } else {
                            myCallback.onFailed("");
                        }
                    }
                });
    }

    @Override
    public void getElectInfoData(int roomId, final MyCallback myCallback) {
        OkHttpUtils.get().url(ApplicationParam.ELECT_GETMONEY_API)
                .addParams("roomId", roomId + "").build()
                .execute(new MessageCallback() {
                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                            myCallback.onSuccess(new Gson().fromJson(message.getData(), Elect.class));
                        } else {
                            myCallback.onFailed("");
                        }
                    }
                });
    }

    @Override
    public void getWaterInfoData(int waterId, final MyCallback myCallback) {
        OkHttpUtils.get().url(ApplicationParam.WATER_GETMONEY_API)
                .addParams("waterId", waterId + "").build()
                .execute(new MessageCallback() {
                    @Override
                    public void onResponse(Message message, int id) {
                        if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                            myCallback.onSuccess(new Gson().fromJson(message.getData(), Water.class));
                        } else {
                            myCallback.onFailed("");
                        }
                    }
                });
    }

    @Override
    public void airRechargeData(int airId, final int money, final MyCallback myCallback) {
        Air air = new Air();
        air.setAirId(airId);
        air.setAirMoney(money);
        OkHttpUtils.postString().url(ApplicationParam.AIR_RECHARGE_API)
                .content(new Gson().toJson(air))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                } else {
                    myCallback.onFailed(message.getData());
                }
            }
        });
    }

    @Override
    public void electRechargeData(Integer electId, int money, final MyCallback myCallback) {
        Elect elect = new Elect();
        elect.setElectId(electId);
        elect.setElectMoney(money);
        OkHttpUtils.postString().url(ApplicationParam.ELECT_RECHARGE_API)
                .content(new Gson().toJson(elect))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                } else {
                    myCallback.onFailed(message.getData());
                }
            }
        });
    }

    @Override
    public void waterRechargeData(Integer waterId, int money, final MyCallback myCallback) {
        Water water = new Water();
        water.setWaterId(waterId);
        water.setWaterMoney(money);
        Log.v("water", water.toString());
        OkHttpUtils.postString().url(ApplicationParam.WATER_RECHARGE_API)
                .content(new Gson().toJson(water))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MessageCallback() {
            @Override
            public void onResponse(Message message, int id) {
                if (message.getStatus() == ApplicationParam.STATUS_SUCCESS) {
                    myCallback.onSuccess(message.getData());
                } else {
                    myCallback.onFailed(message.getData());
                }
            }
        });
    }

    @Override
    public void getUserAllRechargeData(final MyCallback myCallback) {
        OkHttpUtils.get().url(ApplicationParam.GETUSERALLRECHARGE_API)
                .build()
                .execute(new MessageCallback() {
                    @Override
                    public void onResponse(Message message, int id) {
                        Log.v("recharge", message.getData());
                        if (message.getData() != null) {
                            myCallback.onSuccess(message.getData());
                        } else {
                            myCallback.onFailed("未查询到相关充值信息");
                        }
                    }
                });
    }
}
