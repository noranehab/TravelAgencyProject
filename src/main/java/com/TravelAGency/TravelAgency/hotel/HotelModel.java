package com.TravelAGency.TravelAgency.hotel;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel ;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Hotels")
public class HotelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int starRating;

    @Column(nullable = false)
    private String phoneNumber;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomModel> rooms;


    public List<RoomModel> getRooms() {
        return rooms;
    }
}
