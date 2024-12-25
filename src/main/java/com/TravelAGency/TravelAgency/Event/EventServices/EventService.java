package com.TravelAGency.TravelAgency.Event.EventServices;

import com.TravelAGency.TravelAgency.Event.EventDto;
import com.TravelAGency.TravelAgency.Event.EventModel;
import com.TravelAGency.TravelAgency.Event.EventRepository;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private static final String API_URL = "http://localhost:3001/events"; // Mock API URL

    private final RestTemplate restTemplate;

    @Autowired
    public EventService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<EventDto> fetchEventsFromApi() {
        try {
            EventModel[] events = restTemplate.getForObject(API_URL, EventModel[].class);
            if (events != null) {
                return mapToEventDto(events);
            }
        } catch (Exception e) {
            System.err.println("Error fetching events: " + e.getMessage());
        }
        return List.of();
    }

    @Autowired
    private EventRepository eventRepository;

    // Fetch events related to the booked hotel
    public List<EventModel> getEventsForHotel(HotelModel hotel) {
        // Fetch events from the database that are related to the specified hotel
        return eventRepository.findByHotel(hotel);
    }
    public List<EventDto> searchEventsByLocation(String location) {
        List<EventDto> events = fetchEventsFromApi();
        return events.stream()
                .filter(event -> event.getAddress() != null && event.getAddress().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }


    private List<EventDto> mapToEventDto(EventModel[] events) {
        return List.of(events).stream()
                .map(event -> new EventDto(
                        event.getId(),
                        event.getEventName(),
                        event.getEvent_type(),
                        event.getAddress(),
                        event.getTicket()
                ))
                .collect(Collectors.toList());
    }
}
