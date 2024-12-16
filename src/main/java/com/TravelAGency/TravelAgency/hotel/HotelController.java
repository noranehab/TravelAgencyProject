package com.TravelAGency.TravelAgency.hotel;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel ;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.TravelAGency.TravelAgency.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public HotelModel createHotel(@RequestBody HotelModel hotelModel) {
        return hotelRepository.save(hotelModel); // Save the hotel to the database
    }

    @GetMapping
    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll(); // Retrieve all hotels from the database
    }

    @PostMapping("/{hotelId}/rooms")
    public HotelModel addRoomToHotel(@PathVariable Long hotelId, @RequestBody RoomModel roomModel) {
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found!"));
        roomModel.setHotel(hotel);
        hotel.getRooms().add(roomModel);
        return hotelRepository.save(hotel);
    }



}
