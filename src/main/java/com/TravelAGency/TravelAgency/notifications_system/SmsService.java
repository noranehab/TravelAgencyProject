package com.TravelAGency.TravelAgency.notifications_system;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSms(int phoneNumber, String message) {

        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
