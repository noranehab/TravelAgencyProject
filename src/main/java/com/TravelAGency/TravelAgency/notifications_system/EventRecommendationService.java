package com.TravelAGency.TravelAgency.notifications_system;
import com.TravelAGency.TravelAgency.Event.EventDto;
import com.TravelAGency.TravelAgency.Event.EventServices.EventService;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel;
import com.TravelAGency.TravelAgency.User.UserModel;

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
        String roomAddress = room.getHotell().getAddress(); // Ensure HotelModel has a valid address
        System.out.println("Room Address: " + roomAddress);

        if (roomAddress == null || roomAddress.isEmpty()) {
            System.out.println("Room address is null or empty. Skipping recommendations.");
            return;
        }


        List<EventDto> events = eventService.fetchEventsFromApi();
        System.out.println("Fetched Events: " + events);


        List<EventDto> recommendedEvents = events.stream()
                .filter(event -> event.getAddress() != null && event.getAddress().equalsIgnoreCase(roomAddress))
                .collect(Collectors.toList());
        System.out.println("Recommended Events: " + recommendedEvents);

        if (recommendedEvents.isEmpty()) {
            System.out.println("No recommended events found for the room address.");
            return;
        }


        StringBuilder message = new StringBuilder("Hello " + user.getName() + ",\n\n"
                + "You have successfully booked a room at " + room.getHotell().getName() + ".\n\n"
                + "Here are some events you may be interested in, located at the same address:\n");

        for (EventDto event : recommendedEvents) {
            message.append("- " + event.getEventName() + "\n");
        }

        message.append("\nEnjoy your stay and have a great time!");
        System.out.println("Notification Message: " + message);

        // Send SMS notification with recommended events
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message.toString());
    }
}