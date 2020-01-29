package com.zijing.schoolonline.callback;

import java.util.List;

public interface ListCallBack {
    void onSuccess(List<Object> list);

    void onFailed();
}
