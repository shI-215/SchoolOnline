package com.zijing.schoolonline.presenter;

public interface RoomPresenter {
    void getRoomArea();

    void onDestroy();

    void getRoomDoorplate(String area);

    void bindingRoom(Long userId, String area, String doorplate);
}
