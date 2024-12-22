package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    List<RoomModel> findByHotel_NameAndRoomType(String hotelName, RoomSpec roomType);
}

