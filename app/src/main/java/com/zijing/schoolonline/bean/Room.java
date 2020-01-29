package com.zijing.schoolonline.bean;

public class Room {

    /**
     * roomArea : 一栋
     * roomDoorplate : 108
     * roomId : 202001108
     * roomPopulation : 0
     * roomType : 4
     * roomRemark : null
     */

    private String roomArea;
    private int roomDoorplate;
    private int roomId;
    private int roomPopulation;
    private int roomType;
    private String roomRemark;

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public int getRoomDoorplate() {
        return roomDoorplate;
    }

    public void setRoomDoorplate(int roomDoorplate) {
        this.roomDoorplate = roomDoorplate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomPopulation() {
        return roomPopulation;
    }

    public void setRoomPopulation(int roomPopulation) {
        this.roomPopulation = roomPopulation;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRoomRemark() {
        return roomRemark;
    }

    public void setRoomRemark(String roomRemark) {
        this.roomRemark = roomRemark;
    }
}
