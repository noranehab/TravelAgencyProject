package com.TravelAGency.TravelAgency.Event;

import com.TravelAGency.TravelAgency.Event.EventServices.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @RequestMapping(value = "/DisplayEvent", method = RequestMethod.GET)
    public List<EventDto> displayEvents() {
        return eventService.fetchEventsFromApi();
    }

    @RequestMapping(value = "/SearchEvent", method = RequestMethod.GET)
    public List<EventDto> searchEventsByLocation(@RequestParam String location) {
        return eventService.searchEventsByLocation(location);
    }
}
