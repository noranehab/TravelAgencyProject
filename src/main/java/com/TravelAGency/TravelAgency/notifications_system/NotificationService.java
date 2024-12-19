package com.TravelAGency.TravelAgency.notifications_system;

import com.TravelAGency.TravelAgency.User.UserModel;
//import com.TravelAGency.TravelAgency.notifications_system.Booking_Notification.BookingNotificationRequest;
//import com.TravelAGency.TravelAgency.notifications_system.Booking_Notification.BookingNotificationService;
//import com.TravelAGency.TravelAgency.notifications_system.PasswordRest_Notification.PasswordResetNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends BaseNotificationService {

 //   @Autowired
    //private PasswordResetNotificationService passwordResetNotificationService;

   // @Autowired
    //private BookingNotificationService bookingNotificationService;

    public void sendPasswordResetNotification(UserModel user, String massage) {
sendSms(user.getEmail(), massage);
         sendEmail(user.getEmail(), massage);
    }
    public void sendSmsNotification(String phoneNumber, String message) {
       sendSms(phoneNumber, message);

    }


}