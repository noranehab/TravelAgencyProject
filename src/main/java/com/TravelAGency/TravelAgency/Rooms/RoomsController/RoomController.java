package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.User.dto.RoomDto;
import com.TravelAGency.TravelAgency.services.RoomServices; // Correct import
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")

public class RoomController {

    private final RoomServices roomService;

    public RoomController(RoomServices roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {
        boolean success = roomService.postRoom(roomDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
