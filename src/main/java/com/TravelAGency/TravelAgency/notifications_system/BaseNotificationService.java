package com.TravelAGency.TravelAgency.notifications_system;

import org.springframework.stereotype.Service;

@Service
public abstract class BaseNotificationService {
    protected void sendEmail(String name,  String content) {
        System.out.println("Sending Email to " + name +  " and Content: " + content);
    }

    protected void sendSms(String name, String content) {
        System.out.println("Sending SMS to " + name + " with Content: " + content);
    }
}
