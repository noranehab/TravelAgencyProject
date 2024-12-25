package com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel;
import com.TravelAGency.TravelAgency.User.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "UserRooms")
public class UserRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private RoomModel room;

    private long price;
    private long roomNumber;
    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    @Column(name = "room_type")
    private RoomSpec roomType;

    public long getnumberOfNights() {
        return numberOfNights;
    }

    public void setnumberOfNights(long setnumberOfNights) {
        this.numberOfNights = setnumberOfNights;
    }

    private long numberOfNights;

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
       // this.roomType = room != null ? room.getRoomType() : null;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }
    public RoomSpec getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomSpec roomType) {
        this.roomType = roomType;
    }
}
