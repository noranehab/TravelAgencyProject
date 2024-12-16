package com.TravelAGency.TravelAgency.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {

    // Custom queries can be added here if needed
}
