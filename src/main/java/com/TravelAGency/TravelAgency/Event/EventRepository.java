package com.TravelAGency.TravelAgency.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, Long> {
    List<EventModel> findByHotelId(Long hotelId);
}
