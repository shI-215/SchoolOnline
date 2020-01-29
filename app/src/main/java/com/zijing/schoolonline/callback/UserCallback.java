package com.zijing.schoolonline.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.bean.User;

import okhttp3.Response;

public abstract class UserCallback extends Callback<User> {
    @Override
    public User parseNetworkResponse(Response response, int id) throws Exception {
        Message message = new Gson().fromJson(response.body().string(), Message.class);
        return new Gson().fromJson(message.getData(), User.class);
    }
}
