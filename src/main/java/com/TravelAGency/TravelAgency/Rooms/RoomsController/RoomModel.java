package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
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
}
