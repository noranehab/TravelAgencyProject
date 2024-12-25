package com.TravelAGency.TravelAgency.notifications_system;

import com.TravelAGency.TravelAgency.Event.EventDto;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel ;
import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.Event.EventServices.EventService ;
import com.TravelAGency.TravelAgency.notifications_system.NotificationService ;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRecommendationService {

    private final EventService eventService;
    private final NotificationService notificationService;

    public EventRecommendationService(EventService eventService, NotificationService notificationService) {
        this.eventService = eventService;
        this.notificationService = notificationService;
    }

    public void sendRecommendedEvents(UserModel user, RoomModel room) {
        // Get the address of the room
        String roomAddress = room.getHotel2().getAddress();// Assuming the RoomModel has a Hotel reference with the address

        // Fetch all events
        List<EventDto> events = eventService.fetchEventsFromApi();

        // Filter events by address
        List<EventDto> recommendedEvents = events.stream()
                .filter(event -> event.getAddress().equalsIgnoreCase(roomAddress))
                .collect(Collectors.toList());

        if (recommendedEvents.isEmpty()) {
            // If no recommended events are found, just return without sending a notification
            return;
        }

        // Build the SMS message
        StringBuilder message = new StringBuilder("Hello " + user.getName() + ",\n\n"
                + "You have successfully booked a room at " + room.getHotel() + ".\n\n"
                + "Here are some events you may be interested in, located at the same address:\n");

        for (EventDto event : recommendedEvents) {
            message.append("- " + event.getEventName() + " on " + "\n");
        }

        message.append("\nEnjoy your stay and have a great time!");

        // Send SMS notification with recommended events
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message.toString());
    }
}
