package com.zijing.schoolonline.bean;

public class User {


    /**
     * room : {"roomId":202001101}
     * userAutograph : 走自己的路，让别人去说吧。
     * userBirthday : 2020-01-18 16:00:30
     * userId : 20201010001
     * userName : hello
     * userPassword : 12345678900
     * userPhone : 13798001140
     * userPicture : /images/1583208709574.png
     * userSex : 男
     * water : {"waterId":202010101,"waterMoney":0}
     */

    private String userAutograph;
    private String userBirthday;
    private long userId;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userPicture;
    private String userSex;
    private Room room;
    private Water water;

    @Override
    public String toString() {
        return "User{" +
                "userAutograph='" + userAutograph + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userSex='" + userSex + '\'' +
                ", room=" + room +
                ", water=" + water +
                '}';
    }

    public String getUserAutograph() {
        return userAutograph;
    }

    public void setUserAutograph(String userAutograph) {
        this.userAutograph = userAutograph;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }
}
