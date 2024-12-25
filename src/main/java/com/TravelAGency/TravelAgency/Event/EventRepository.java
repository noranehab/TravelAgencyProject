package com.TravelAGency.TravelAgency.Event;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<UserEvents, Long> {
    Optional<UserEvents> findByUserIdAndEventName(Integer userId, String eventName);
    List<UserEvents> findByUser(UserModel user);
    List<EventModel> findByHotel(HotelModel hotel);
}
