package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.RoomCallBack;
import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.RoomView;

import java.util.List;

public class RoomPresenterImpl implements RoomPresenter, RoomCallBack {
    private RoomView roomView;
    private UserModel userModel;

    public RoomPresenterImpl(RoomView roomView) {
        this.roomView = roomView;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void onSuccess(List<Object> list) {
        if (roomView != null) {
            roomView.onSuccess(list);
        }
    }

    @Override
    public void onFailed() {
        if (roomView != null) {
            roomView.onFailed();
        }
    }


    @Override
    public void onDestroy() {
        roomView = null;
    }

    @Override
    public void getRoomDoorplate(String area) {
        userModel.getRoomDoorplateData(area, this);
    }

    @Override
    public void bindingRoom(Long userId, String area, String doorplate) {
        userModel.bindingRoomData(userId, area, doorplate, this);
    }

    @Override
    public void getRoomArea() {
        userModel.getRoomAreaData(this);
    }
}
