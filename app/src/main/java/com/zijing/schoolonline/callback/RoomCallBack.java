package com.zijing.schoolonline.callback;

import java.util.List;

public interface RoomCallBack {
    void onSuccess(List<Object> list);

    void onFailed();
}
