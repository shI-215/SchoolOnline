package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.bean.Room;
import com.zijing.schoolonline.callback.RoomCallBack;
import com.zijing.schoolonline.model.RoomModel;
import com.zijing.schoolonline.model.RoomModelImpl;
import com.zijing.schoolonline.view.RoomListening;

public class RoomPresenterImpl implements RoomPresenter, RoomCallBack {
    private RoomListening roomListening;
    private RoomModel roomModel;

    public RoomPresenterImpl(RoomListening roomListening) {
        this.roomListening = roomListening;
        this.roomModel = new RoomModelImpl();
    }

    @Override
    public void onSuccess(Room room) {
        if (roomListening != null) {
            roomListening.onSuccess(room);
        }
    }

    @Override
    public void onFailed() {
        if (roomListening != null) {
            roomListening.onFailed();
        }
    }

    @Override
    public void onDestroy() {
        roomListening = null;
    }

    @Override
    public void getRoomInfo(int roomId) {
        roomModel.getRoomInfo(roomId, this);
    }
}
