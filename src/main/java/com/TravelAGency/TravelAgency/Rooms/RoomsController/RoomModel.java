package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table(name="Rooms")
@Component
@Scope("prototype")

public class RoomModel
{
    @Id
    private Long RoomId;
    private Integer RoomNumber;
    private String Hotel;
    private RoomSpec RoomType;
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    public Integer getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getHotel() {
        return Hotel;
    }

    public void setHotel(String hotel) {
        Hotel = hotel;
    }

    public RoomSpec getRoomType() {
        return RoomType;
    }

    public void setRoomType(RoomSpec roomType) {
        RoomType = roomType;
    }



    public void setId(Long id) {
        this.RoomId = id;
    }

    public Long getId() {
        return RoomId;
    }

    // Many rooms can belong to one hotel
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelModel hotel; // Each room belongs to a hotel

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }
}
