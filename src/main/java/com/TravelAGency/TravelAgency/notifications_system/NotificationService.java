package com.TravelAGency.TravelAgency.notifications_system;

import com.TravelAGency.TravelAgency.User.UserModel;
//import com.TravelAGency.TravelAgency.notifications_system.Booking_Notification.BookingNotificationRequest;
//import com.TravelAGency.TravelAgency.notifications_system.Booking_Notification.BookingNotificationService;
//import com.TravelAGency.TravelAgency.notifications_system.PasswordRest_Notification.PasswordResetNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends BaseNotificationService {
    @Autowired
    private NotificationStatisticsService notificationStatisticsService;

    public void sendPasswordResetNotification(UserModel user, String massage) {
       try {
           sendSms(user.getEmail(), massage);
           sendEmail(user.getEmail(), massage);
           notificationStatisticsService.incrementEmailNotifications();
           notificationStatisticsService.incrementSmsNotifications();
       }
       catch (Exception e) {notificationStatisticsService.incrementFailedNotifications();}

    }
    public void sendSmsNotification(String phoneNumber, String message) {
     try {
         sendSms(phoneNumber, message);

         notificationStatisticsService.incrementSmsNotifications();
     }
     catch (Exception e) {notificationStatisticsService.incrementFailedNotifications();}
    }


}
