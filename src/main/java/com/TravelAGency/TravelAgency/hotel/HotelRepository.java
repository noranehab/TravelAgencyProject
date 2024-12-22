package com.TravelAGency.TravelAgency.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    @Query("SELECT h FROM HotelModel h WHERE h.name = :name")
    Optional<HotelModel> findFirstByName(String name);
}
