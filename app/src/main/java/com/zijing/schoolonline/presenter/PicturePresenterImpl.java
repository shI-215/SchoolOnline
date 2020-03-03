package com.zijing.schoolonline.presenter;

import com.zijing.schoolonline.model.UserModel;
import com.zijing.schoolonline.model.UserModelImpl;
import com.zijing.schoolonline.view.PictureListening;

import java.io.File;

public class PicturePresenterImpl implements PicturePresenter, PictureListening {
    private PictureListening pictureListening;
    private UserModel userModel;

    public PicturePresenterImpl(PictureListening pictureListening) {
        this.pictureListening = pictureListening;
        this.userModel = new UserModelImpl();
    }

    @Override
    public void alterPicture(File file) {
        userModel.alterPictureData(file, this);
    }

    @Override
    public void onSuccess(String string) {
        if (pictureListening != null) pictureListening.onSuccess(string);
    }

    @Override
    public void onFailed(String string) {
        if (pictureListening != null) pictureListening.onFailed(string);
    }

    @Override
    public void onDestroy() {
        pictureListening = null;
    }
}
