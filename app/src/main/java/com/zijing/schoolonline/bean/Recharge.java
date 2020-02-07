package com.zijing.schoolonline.bean;

public class Recharge {
    private Integer rechargeId;
    private String rechargeAccount;
    private double rechargeMoney;
    private String rechargeType;
    private String rechargeDate;
    private Long rechargeUser;
    private String rechargeRemark;

    @Override
    public String toString() {
        return "Recharge{" +
                "rechargeId=" + rechargeId +
                ", rechargeAccount='" + rechargeAccount + '\'' +
                ", rechargeMoney=" + rechargeMoney +
                ", rechargeType='" + rechargeType + '\'' +
                ", rechargeDate='" + rechargeDate + '\'' +
                ", rechargeUser=" + rechargeUser +
                ", rechargeRemark='" + rechargeRemark + '\'' +
                '}';
    }

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(String rechargeAccount) {
        this.rechargeAccount = rechargeAccount;
    }

    public double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Long getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(Long rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    public String getRechargeRemark() {
        return rechargeRemark;
    }

    public void setRechargeRemark(String rechargeRemark) {
        this.rechargeRemark = rechargeRemark;
    }
}
