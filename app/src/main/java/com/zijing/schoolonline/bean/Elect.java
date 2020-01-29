package com.zijing.schoolonline.bean;

public class Elect {
    private Integer electId;
    private double electMoney;
    private String electRemark;
    private Room room;

    @Override
    public String toString() {
        return "Elect{" +
                "electId=" + electId +
                ", electMoney=" + electMoney +
                ", electRemark='" + electRemark + '\'' +
                ", room=" + room +
                '}';
    }

    public Integer getElectId() {
        return electId;
    }

    public void setElectId(Integer electId) {
        this.electId = electId;
    }

    public double getElectMoney() {
        return electMoney;
    }

    public void setElectMoney(double electMoney) {
        this.electMoney = electMoney;
    }

    public String getElectRemark() {
        return electRemark;
    }

    public void setElectRemark(String electRemark) {
        this.electRemark = electRemark;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
