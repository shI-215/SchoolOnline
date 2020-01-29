package com.zijing.schoolonline.bean;

public class Air {
    private Integer airId;
    private double airMoney;
    private String airRemark;
    private Room room;

    @Override
    public String toString() {
        return "Air{" +
                "airId=" + airId +
                ", airMoney=" + airMoney +
                ", airRemark='" + airRemark + '\'' +
                ", room=" + room +
                '}';
    }

    public Integer getAirId() {
        return airId;
    }

    public void setAirId(Integer airId) {
        this.airId = airId;
    }

    public double getAirMoney() {
        return airMoney;
    }

    public void setAirMoney(double airMoney) {
        this.airMoney = airMoney;
    }

    public String getAirRemark() {
        return airRemark;
    }

    public void setAirRemark(String airRemark) {
        this.airRemark = airRemark;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
