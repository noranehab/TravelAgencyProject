package com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms;

import com.TravelAGency.TravelAgency.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoomRepo extends JpaRepository<UserRooms, Long> {
    List<UserRooms> findByUser(UserModel user);

}
