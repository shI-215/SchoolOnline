package com.zijing.schoolonline.view;

public interface MainView {
    //    成功
    void onSuccess(Object object);

    //失败
    void onFailed(Object object);

    //异常
    void onError(Object object);
}
