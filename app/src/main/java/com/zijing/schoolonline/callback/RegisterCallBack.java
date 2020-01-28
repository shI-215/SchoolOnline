package com.zijing.schoolonline.callback;

import com.zijing.schoolonline.bean.Message;

public interface RegisterCallBack {
    void onSuccess();

    void onFailed(Message message);

    void onError();
}
