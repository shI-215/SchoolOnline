package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.bean.Room;
import com.zijing.schoolonline.callback.RoomCallBack;
import com.zijing.schoolonline.model.RoomModel;
import com.zijing.schoolonline.model.RoomModelImpl;
import com.zijing.schoolonline.view.RoomView;

public class RoomPresenterImpl implements RoomPresenter, RoomCallBack {
    private RoomView roomView;
    private RoomModel roomModel;

    public RoomPresenterImpl(RoomView roomView) {
        this.roomView = roomView;
        this.roomModel = new RoomModelImpl();
    }

    @Override
    public void onSuccess(Room room) {
        if (roomView != null) {
            roomView.onSuccess(room);
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
    public void getRoomInfo(int roomId) {
        roomModel.getRoomInfo(roomId, this);
    }
}
