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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomId;

    private String Hotel;
    private RoomSpec RoomType;
    public String name;
    private long price;
    private boolean available;
    private Integer RoomNumber;
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
    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
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
