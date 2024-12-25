package com.TravelAGency.TravelAgency.notifications_system;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Component
public class NotificationStatisticsService {

    private AtomicInteger totalNotificationsSent = new AtomicInteger(0);
    private AtomicInteger emailNotificationsSent = new AtomicInteger(0);
    private AtomicInteger smsNotificationsSent = new AtomicInteger(0);
    private AtomicInteger FailedNotificationsSent = new AtomicInteger(0);

    public void incrementTotalNotifications() {
        totalNotificationsSent.incrementAndGet();

    }


    public void incrementEmailNotifications() {
        emailNotificationsSent.incrementAndGet();
        incrementTotalNotifications();
    }

    public void incrementSmsNotifications() {
        smsNotificationsSent.incrementAndGet();
        incrementTotalNotifications();
    }

    public void incrementFailedNotifications() {
       incrementFailedNotifications();
    }





    public Map<String, Integer> getNotificationStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalNotificationsSent", totalNotificationsSent.get());
        stats.put("emailNotificationsSent", emailNotificationsSent.get());
        stats.put("smsNotificationsSent", smsNotificationsSent.get());
        stats.put("FailedNotificationsSent", FailedNotificationsSent.get());
        return stats;
    }
}
