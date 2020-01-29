package com.zijing.schoolonline.callback;

import com.zijing.schoolonline.bean.User;

public interface LoginCallBack {
    void onSuccess(User user);

    void onFailed();
}
