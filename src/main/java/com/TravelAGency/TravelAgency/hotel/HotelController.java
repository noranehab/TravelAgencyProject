package com.TravelAGency.TravelAgency.hotel;

import com.TravelAGency.TravelAgency.Event.EventModel;
import com.TravelAGency.TravelAgency.Event.EventRepository;
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
    private EventRepository eventRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public HotelModel createHotel(@RequestBody HotelModel hotelModel) {
        return hotelRepository.save(hotelModel);
    }

    @GetMapping
    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @PostMapping("/{hotelId}/rooms")
    public HotelModel addRoomToHotel(@PathVariable Long hotelId, @RequestBody RoomModel roomModel) {
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found!"));
        roomModel.setHotel(hotel);
        hotel.getRooms().add(roomModel);
        return hotelRepository.save(hotel);
    }
    @PostMapping("/{hotelId}/events")
    public EventModel addEventToHotel(@PathVariable Long hotelId, @RequestBody EventModel eventModel) {
        // Find the hotel by its ID
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found!"));


        eventModel.setHotel(hotel);


        return eventRepository.save(eventModel);
    }




}
