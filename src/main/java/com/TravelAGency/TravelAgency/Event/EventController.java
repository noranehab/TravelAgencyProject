package com.TravelAGency.TravelAgency.Event;

import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.TravelAGency.TravelAgency.hotel.HotelRepository;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HotelRepository hotelRepository;


    @PostMapping("/{hotelId}")
    public EventModel addEventToHotel(@PathVariable Long hotelId, @RequestBody EventModel eventModel) {
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found!"));

        eventModel.setHotel(hotel);  // Set the hotel for the event
        return eventRepository.save(eventModel);
    }


    @GetMapping("/{hotelId}")
    public List<EventModel> getEventsByHotel(@PathVariable Long hotelId) {
        return eventRepository.findByHotelId(hotelId);
    }


    @GetMapping("/{hotelId}/{eventId}")
    public EventModel getEventById(@PathVariable Long hotelId, @PathVariable Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found!"));
    }
}
