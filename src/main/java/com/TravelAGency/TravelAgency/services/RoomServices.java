package com.TravelAGency.TravelAgency.services;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomDto.RoomDto;

import java.util.List;

public interface RoomServices {
     List<RoomDto> SearchForRoom(RoomDto roomDto);

}
