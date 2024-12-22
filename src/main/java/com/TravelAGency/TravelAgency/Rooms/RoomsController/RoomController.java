package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomDto.RoomDto;
import com.TravelAGency.TravelAgency.services.RoomServices; // Correct import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")

public class RoomController {

    private final RoomServices roomService;
    private final RoomRepository roomRepository;
    public RoomController(RoomServices roomService, RoomRepository roomRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }


    @RequestMapping(value = "/viewRooms", method = RequestMethod.GET)
    public List<RoomModel> getRooms() {
        return roomRepository.findAll();
    }

    @RequestMapping(value = "/SearchRoom", method = RequestMethod.POST)
    public ResponseEntity<List<RoomDto>> searchRooms(@RequestBody RoomDto roomDto) {
        List<RoomDto> rooms = roomService.SearchForRoom(roomDto);
        return ResponseEntity.ok(rooms);
    }
}
