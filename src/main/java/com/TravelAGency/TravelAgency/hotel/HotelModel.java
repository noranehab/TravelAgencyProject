package com.TravelAGency.TravelAgency.hotel;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel ;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RoomModel> rooms;


    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setHotelName(String hotelName) {
        name = hotelName;
    }
}
