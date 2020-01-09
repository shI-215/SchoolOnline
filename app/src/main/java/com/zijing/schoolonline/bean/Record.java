package com.zijing.schoolonline.bean;

public class Record {
    private int rid;
    private String account;
    private double amount;
    private String time;

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
