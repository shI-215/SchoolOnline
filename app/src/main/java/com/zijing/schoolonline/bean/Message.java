package com.zijing.schoolonline.bean;

public class Message {

    /**
     * data : null
     * msg : success
     * status : 0
     */

    private String data;
    private String msg;
    private int status;

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
