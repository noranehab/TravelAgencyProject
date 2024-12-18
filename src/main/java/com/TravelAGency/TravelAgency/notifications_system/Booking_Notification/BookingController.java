package com.TravelAGency.TravelAgency.notifications_system.Booking_Notification;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.notifications_system.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NotificationService notificationService;

    // Endpoint to book a hotel
    @RequestMapping(value = "/book-hotel", method = RequestMethod.POST)
    public ResponseEntity<String> bookHotel(@RequestBody BookingRequest bookingRequest) {
        Optional<UserModel> userOptional = Optional.ofNullable(userRepo.findFirstByEmail(bookingRequest.getUserEmail()));
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>("No user found with the provided email.", HttpStatus.NOT_FOUND);
        }

        String bookingDetails = "Hotel " + bookingRequest.getHotelName() + " booking confirmed for " + bookingRequest.getCheckInDate() + " to " + bookingRequest.getCheckOutDate();

        BookingNotificationRequest bookingNotificationRequest = new BookingNotificationRequest();
        bookingNotificationRequest.setUserEmail(bookingRequest.getUserEmail());
        bookingNotificationRequest.setBookingDetails(bookingDetails);

        notificationService.sendBookingNotification(bookingNotificationRequest);

        return new ResponseEntity<>("Hotel booking confirmed. A notification has been sent.", HttpStatus.OK);
    }

    // Endpoint to book an event
    @RequestMapping(value = "/book-event", method = RequestMethod.POST)
    public ResponseEntity<String> bookEvent(@RequestBody BookingRequest bookingRequest) {
        Optional<UserModel> userOptional = Optional.ofNullable(userRepo.findFirstByEmail(bookingRequest.getUserEmail()));
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>("No user found with the provided email.", HttpStatus.NOT_FOUND);
        }

        // Create a booking confirmation message for the event
        String bookingDetails = "Event " + bookingRequest.getEventName() + " booking confirmed for " + bookingRequest.getEventDate();

        // Create BookingNotificationRequest for event booking
        BookingNotificationRequest bookingNotificationRequest = new BookingNotificationRequest();
        bookingNotificationRequest.setUserEmail(bookingRequest.getUserEmail());
        bookingNotificationRequest.setBookingDetails(bookingDetails);

        // Send booking confirmation notification (SMS or Email)
        notificationService.sendBookingNotification(bookingNotificationRequest);

        return new ResponseEntity<>("Event booking confirmed. A notification has been sent.", HttpStatus.OK);
    }
}

