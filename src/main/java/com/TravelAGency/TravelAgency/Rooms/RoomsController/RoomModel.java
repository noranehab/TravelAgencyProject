package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table(name = "Rooms")
@Component
@Scope("prototype")
public class RoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-generate roomId
    private Long roomId;
    private long price;
    private boolean available;
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    @Column(name = "room_type")
    private RoomSpec roomType;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelModel hotel;  // Each room belongs to a hotel

    // Getters and Setters

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHotel() {
        return hotel != null ? hotel.getName() : null; // Get hotel name (check if hotel is not null)
    }
    public HotelModel getHotel2() {
        return hotel;
    }
    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }

    public RoomSpec getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomSpec roomType) {
        this.roomType = roomType;
    }



    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Long getRoomId() {
        return roomId;
    }
}
