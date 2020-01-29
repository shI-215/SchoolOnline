package com.zijing.schoolonline.callback;

public interface MyCallback<T> {
    void onSuccess(T t);

    void onFailed(T t);
}
