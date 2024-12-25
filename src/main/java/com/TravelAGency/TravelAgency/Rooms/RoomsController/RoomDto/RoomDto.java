package com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomDto;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;

public class RoomDto {

    private Long roomId;
    private Integer roomNumber;
    private String hotel;
    private RoomSpec roomType;
    private String name;
    private long price;
    private boolean available;

    // Getter and Setter for roomId
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    // Getter and Setter for roomNumber
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getter and Setter for hotel
    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    // Getter and Setter for roomType
    public RoomSpec getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomSpec roomType) {
        this.roomType = roomType;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for price
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    // Getter and Setter for available
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "hotel='" + hotel + '\'' +
                ", roomType=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomDto roomDto = (RoomDto) o;

        if (price != roomDto.price) return false;
        if (available != roomDto.available) return false;
        if (!roomId.equals(roomDto.roomId)) return false;
        if (!roomNumber.equals(roomDto.roomNumber)) return false;
        if (!hotel.equals(roomDto.hotel)) return false;
        if (roomType != roomDto.roomType) return false;
        return name.equals(roomDto.name);
    }

    @Override
    public int hashCode() {
        int result = roomId.hashCode();
        result = 31 * result + roomNumber.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
