package com.zijing.schoolonline.presenter;

public interface ListPresenter {
    void getRoomArea();

    void onDestroy();

    void getRoomDoorplate(String area);

    void bindingRoom(String area, String doorplate);
}
