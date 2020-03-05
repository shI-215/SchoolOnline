package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.ListCallBack;
import com.zijing.schoolonline.model.RoomModel;
import com.zijing.schoolonline.model.RoomModelImpl;
import com.zijing.schoolonline.view.ListListening;

import java.util.List;

public class ListPresenterImpl implements ListPresenter, ListCallBack {
    private ListListening listListening;
    private RoomModel roomModel;

    public ListPresenterImpl(ListListening listListening) {
        this.listListening = listListening;
        this.roomModel = new RoomModelImpl();
    }

    @Override
    public void onSuccess(List<Object> list) {
        if (listListening != null) {
            listListening.onSuccess(list);
        }
    }

    @Override
    public void onFailed() {
        if (listListening != null) {
            listListening.onFailed();
        }
    }


    @Override
    public void onDestroy() {
        listListening = null;
    }

    @Override
    public void getRoomDoorplate(String area) {
        roomModel.getRoomDoorplateData(area, this);
    }

    @Override
    public void bindingRoom(String area, String doorplate) {
        roomModel.bindingRoomData(area, doorplate, this);
    }

    @Override
    public void getRoomArea() {
        roomModel.getRoomAreaData(this);
    }
}
