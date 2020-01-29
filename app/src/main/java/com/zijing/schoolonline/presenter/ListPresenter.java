package com.zijing.schoolonline.presenter;

public interface ListPresenter {
    void getRoomArea();

    void onDestroy();

    void getRoomDoorplate(String area);

    void bindingRoom(Long userId, String area, String doorplate);
}
