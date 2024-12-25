package com.TravelAGency.TravelAgency.notifications_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class NotificationController {


        @Autowired
        private NotificationStatisticsService notificationStatisticsService;

        @RequestMapping(value = "/notification-statistics", method = RequestMethod.GET)
        public ResponseEntity<Map<String, Integer>> getNotificationStatistics() {
            return new ResponseEntity<>(notificationStatisticsService.getNotificationStatistics(), HttpStatus.OK);
        }
    }



