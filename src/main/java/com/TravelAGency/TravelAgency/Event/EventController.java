package com.TravelAGency.TravelAgency.Event;

import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.TravelAGency.TravelAgency.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class EventController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/{hotelId}/add-event")
    public EventModel addEventToHotel(@PathVariable Long hotelId, @RequestBody EventModel eventModel) {
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found!"));

        eventModel.setHotel(hotel);

        return eventRepository.save(eventModel);
    }
    //comment
}
