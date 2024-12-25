package com.TravelAGency.TravelAgency.User.services.authintication;

import com.TravelAGency.TravelAgency.Event.EventRepository;
import com.TravelAGency.TravelAgency.Event.UserEvents;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms.UserRoomRepo;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms.UserRooms;
import com.TravelAGency.TravelAgency.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private UserRoomRepo userRoomRepo;

    @Autowired
    private EventRepository userEventsRepo;

    public Map<String, Object> getUserBookings(UserModel user) {
        List<UserRooms> roomBookings = userRoomRepo.findByUser(user);
        List<UserEvents> eventBookings = userEventsRepo.findByUser(user);

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("roomBookings", roomBookings);
        dashboard.put("eventBookings", eventBookings);

        return dashboard;
    }
}
