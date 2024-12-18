package com.TravelAGency.TravelAgency.User.dto;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import lombok.Data;

@Data
public class RoomDto {
    private Long RoomId;
    private Integer RoomNumber;
    private String Hotel;
    private RoomSpec RoomType;
    private String name;
    private long price;
    private boolean available;
}
