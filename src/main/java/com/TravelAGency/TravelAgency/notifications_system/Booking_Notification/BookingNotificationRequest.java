package com.TravelAGency.TravelAgency.notifications_system.Booking_Notification;

// DTO for booking notification request
public class BookingNotificationRequest {
    private String userEmail;
    private String bookingDetails;

    // Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(String bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
    
    
    
    
    
    
}
