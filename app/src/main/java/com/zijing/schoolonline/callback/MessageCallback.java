package com.zijing.schoolonline.callback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;
import com.zijing.schoolonline.bean.Message;
import com.zijing.schoolonline.util.ToastUtil;

import okhttp3.Call;
import okhttp3.Response;

public abstract class MessageCallback extends Callback<Message> {
    @Override
    public Message parseNetworkResponse(Response response, int id) throws Exception {
        return new Gson().fromJson(response.body().string(), Message.class);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        ToastUtil.s("请检查网络");
    }
}
