package com.TravelAGency.TravelAgency.services;

import com.TravelAGency.TravelAgency.User.dto.RoomDto;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class roomServicesIm implements RoomServices {
    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto) {
        try {
            RoomModel room = new RoomModel();
            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setRoomType(roomDto.getRoomType());
            room.setAvailable(true);
            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
