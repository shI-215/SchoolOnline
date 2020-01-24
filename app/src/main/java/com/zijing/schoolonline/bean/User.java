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
     * userSex : 男
     * water : {"waterId":202010101,"waterMoney":0}
     */

    private RoomBean room;
    private String userAutograph;
    private String userBirthday;
    private long userId;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userSex;
    private WaterBean water;

    @Override
    public String toString() {
        return "User{" +
                "room=" + room +
                ", userAutograph='" + userAutograph + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userSex='" + userSex + '\'' +
                ", water=" + water +
                '}';
    }

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public WaterBean getWater() {
        return water;
    }

    public void setWater(WaterBean water) {
        this.water = water;
    }

    public static class RoomBean {
        /**
         * roomId : 202001101
         */

        private int roomId;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }
    }

    public static class WaterBean {
        /**
         * waterId : 202010101
         * waterMoney : 0.0
         */

        private int waterId;
        private double waterMoney;

        public int getWaterId() {
            return waterId;
        }

        public void setWaterId(int waterId) {
            this.waterId = waterId;
        }

        public double getWaterMoney() {
            return waterMoney;
        }

        public void setWaterMoney(double waterMoney) {
            this.waterMoney = waterMoney;
        }
    }
}
