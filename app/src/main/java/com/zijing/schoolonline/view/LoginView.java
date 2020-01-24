package com.zijing.schoolonline.view;

import com.zijing.schoolonline.bean.User;

public interface LoginView {
    void onSuccess(User user);

    void onFailed();
}
