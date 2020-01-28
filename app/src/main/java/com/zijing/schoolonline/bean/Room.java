package com.zijing.schoolonline.bean;

public class Room {
    private Integer roomId;
    private String roomArea;
    private Integer roomDoorplate;
    private Integer roomPopulation;
    private Integer roomType;
    private String roomRemark;

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomArea='" + roomArea + '\'' +
                ", roomDoorplate=" + roomDoorplate +
                ", roomPopulation=" + roomPopulation +
                ", roomType=" + roomType +
                ", roomRemark='" + roomRemark + '\'' +
                '}';
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public Integer getRoomDoorplate() {
        return roomDoorplate;
    }

    public void setRoomDoorplate(Integer roomDoorplate) {
        this.roomDoorplate = roomDoorplate;
    }

    public Integer getRoomPopulation() {
        return roomPopulation;
    }

    public void setRoomPopulation(Integer roomPopulation) {
        this.roomPopulation = roomPopulation;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getRoomRemark() {
        return roomRemark;
    }

    public void setRoomRemark(String roomRemark) {
        this.roomRemark = roomRemark;
    }
}
