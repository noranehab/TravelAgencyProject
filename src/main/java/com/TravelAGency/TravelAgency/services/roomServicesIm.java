package com.TravelAGency.TravelAgency.services;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomDto.RoomDto;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class roomServicesIm implements RoomServices {
    private final RoomRepository roomRepository;

    public roomServicesIm(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> searchForRoom(String hotel, RoomSpec roomType) {
        List<RoomModel> rooms = roomRepository.findByHotel_NameAndRoomType(hotel, roomType);
        return rooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RoomDto convertToDto(RoomModel room) {
        RoomDto dto = new RoomDto();
        dto.setRoomId(room.getRoomId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setHotel(room.getHotel());
        dto.setRoomType(room.getRoomType());
        dto.setPrice(room.getPrice());
        dto.setAvailable(room.isAvailable());
        return dto;
    }


}