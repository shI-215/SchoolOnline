package com.zijing.schoolonline.callback;

public interface MainCallback<T> {
    void onSuccess(T t);

    void onFailed(T t);

    void onError(T t);
}
