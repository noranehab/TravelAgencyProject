package com.TravelAGency.TravelAgency.services;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomDto.RoomDto;

import java.util.List;

public interface RoomServices {
     List<RoomDto> searchForRoom(String hotel, RoomSpec roomType);

}
