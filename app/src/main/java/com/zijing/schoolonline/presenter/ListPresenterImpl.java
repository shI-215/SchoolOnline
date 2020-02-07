package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.callback.ListCallBack;
import com.zijing.schoolonline.model.RoomModel;
import com.zijing.schoolonline.model.RoomModelImpl;
import com.zijing.schoolonline.view.ListView;

import java.util.List;

public class ListPresenterImpl implements ListPresenter, ListCallBack {
    private ListView listView;
    private RoomModel roomModel;

    public ListPresenterImpl(ListView listView) {
        this.listView = listView;
        this.roomModel = new RoomModelImpl();
    }

    @Override
    public void onSuccess(List<Object> list) {
        if (listView != null) {
            listView.onSuccess(list);
        }
    }

    @Override
    public void onFailed() {
        if (listView != null) {
            listView.onFailed();
        }
    }


    @Override
    public void onDestroy() {
        listView = null;
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
