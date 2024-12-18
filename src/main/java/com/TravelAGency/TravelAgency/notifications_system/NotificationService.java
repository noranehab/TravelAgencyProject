package com.TravelAGency.TravelAgency.notifications_system;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.notifications_system.Booking_Notification.BookingNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    public void sendPasswordResetNotification(UserModel user, String resetToken) {
        // You can send the reset token via SMS or Email
        String message = "To reset your password, use the following token: " + resetToken;

        // Send SMS to the user
        smsService.sendSms(user.getPhoneNumber(), message);

        // Or, alternatively, you can send an email
        emailService.sendEmail(user.getEmail(), "Password Reset Request", message);
    }
    // Method to send booking notification (for both events and hotels)
    public void sendBookingNotification(BookingNotificationRequest bookingNotificationRequest) {
        String userEmail = bookingNotificationRequest.getUserEmail();
        String bookingDetails = bookingNotificationRequest.getBookingDetails();

        // Send email notification (you can replace this with actual email or SMS logic)
        sendEmail(userEmail, "Booking Confirmation", bookingDetails);

        sendSms(userEmail, bookingDetails);
    }

    private void sendEmail(String email, String subject, String content) {
        System.out.println("Sending Email to " + email + " with Subject: " + subject + " and Content: " + content);
    }

    private void sendSms(String phoneNumber, String content) {
        System.out.println("Sending SMS to " + phoneNumber + " with Content: " + content);
    }
}

