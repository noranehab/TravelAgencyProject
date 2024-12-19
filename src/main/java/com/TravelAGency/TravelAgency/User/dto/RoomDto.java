package com.TravelAGency.TravelAgency.User.dto;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;

public class RoomDto {
    private Long RoomId;
    private Integer RoomNumber;
    private String Hotel;
    private RoomSpec RoomType;
    private String name;
    private long price;
    private boolean available;

    // Getter and Setter for RoomId
    public Long getRoomId() {
        return RoomId;
    }

    public void setRoomId(Long roomId) {
        this.RoomId = roomId;
    }

    // Getter and Setter for RoomNumber
    public Integer getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.RoomNumber = roomNumber;
    }

    // Getter and Setter for Hotel
    public String getHotel() {
        return Hotel;
    }

    public void setHotel(String hotel) {
        this.Hotel = hotel;
    }

    // Getter and Setter for RoomType
    public RoomSpec getRoomType() {
        return RoomType;
    }

    public void setRoomType(RoomSpec roomType) {
        this.RoomType = roomType;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Price
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }



    public void setAvailable(boolean available) {
        this.available = available;
    }
}
