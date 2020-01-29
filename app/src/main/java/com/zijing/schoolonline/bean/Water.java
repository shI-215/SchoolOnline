package com.zijing.schoolonline.bean;

public class Water {
    private Integer waterId;
    private double waterMoney;
    private String waterDate;
    private String waterRemark;

    @Override
    public String toString() {
        return "Water{" +
                "waterId=" + waterId +
                ", waterMoney=" + waterMoney +
                ", waterDate='" + waterDate + '\'' +
                ", waterRemark='" + waterRemark + '\'' +
                '}';
    }

    public Integer getWaterId() {
        return waterId;
    }

    public void setWaterId(Integer waterId) {
        this.waterId = waterId;
    }

    public double getWaterMoney() {
        return waterMoney;
    }

    public void setWaterMoney(double waterMoney) {
        this.waterMoney = waterMoney;
    }

    public String getWaterDate() {
        return waterDate;
    }

    public void setWaterDate(String waterDate) {
        this.waterDate = waterDate;
    }

    public String getWaterRemark() {
        return waterRemark;
    }

    public void setWaterRemark(String waterRemark) {
        this.waterRemark = waterRemark;
    }
}
