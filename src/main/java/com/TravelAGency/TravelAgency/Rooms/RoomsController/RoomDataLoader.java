package com.TravelAGency.TravelAgency.Rooms.RoomsController;

import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.TravelAGency.TravelAgency.hotel.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoomDataLoader implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomDataLoader(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Add and save hotels
        HotelModel hotel1 = new HotelModel();
        hotel1.setName("Ocean Breeze Resort");
        hotel1.setAddress("123 Ocean Drive, Coastal City");
        hotel1.setStarRating(5);
        hotel1.setPhoneNumber("123-456-7890");
        hotelRepository.save(hotel1);

        HotelModel hotel2 = new HotelModel();
        hotel2.setName("Snowy Peaks Lodge");
        hotel2.setAddress("456 Mountain Road, Snowville");
        hotel2.setStarRating(4);
        hotel2.setPhoneNumber("234-567-8901");
        hotelRepository.save(hotel2);

        HotelModel hotel3 = new HotelModel();
        hotel3.setName("City Lights Hotel");
        hotel3.setAddress("789 Downtown Ave, Cityscape");
        hotel3.setStarRating(4);
        hotel3.setPhoneNumber("345-678-9012");
        hotelRepository.save(hotel3);

        HotelModel hotel4 = new HotelModel();
        hotel4.setName("Sandy Shores Inn");
        hotel4.setAddress("101 Beach Blvd, Sandy Beach");
        hotel4.setStarRating(3);
        hotel4.setPhoneNumber("456-789-0123");
        hotelRepository.save(hotel4);

        HotelModel hotel5 = new HotelModel();
        hotel5.setName("Rustic River Retreat");
        hotel5.setAddress("202 River Rd, Nature Valley");
        hotel5.setStarRating(5);
        hotel5.setPhoneNumber("567-890-1234");
        hotelRepository.save(hotel5);

        HotelModel hotel6 = new HotelModel();
        hotel6.setName("Golden Gate Suites");
        hotel6.setAddress("303 Golden Gate St, Sunnytown");
        hotel6.setStarRating(5);
        hotel6.setPhoneNumber("678-901-2345");
        hotelRepository.save(hotel6);

        HotelModel hotel7 = new HotelModel();
        hotel7.setName("Desert Oasis");
        hotel7.setAddress("404 Desert Way, Oasis City");
        hotel7.setStarRating(4);
        hotel7.setPhoneNumber("789-012-3456");
        hotelRepository.save(hotel7);

        // Add and save rooms for the hotels
        roomRepository.save(createRoom( 2500, true, 101, hotel1, RoomSpec.Single));
        roomRepository.save(createRoom(1800, true, 102, hotel1, RoomSpec.Double));
        roomRepository.save(createRoom( 5000, false, 201, hotel1, RoomSpec.Family));

        roomRepository.save(createRoom( 1600, true, 101, hotel2, RoomSpec.Single));
        roomRepository.save(createRoom( 4000, true, 102, hotel2, RoomSpec.Double));
        roomRepository.save(createRoom(3500, true, 201, hotel2, RoomSpec.Family));

        roomRepository.save(createRoom( 3500, true, 101, hotel3, RoomSpec.Single));
        roomRepository.save(createRoom( 2500, true, 102, hotel3, RoomSpec.Double));
        roomRepository.save(createRoom( 6000, false, 201, hotel3, RoomSpec.Family));

        roomRepository.save(createRoom( 2200, true, 101, hotel4, RoomSpec.Single));
        roomRepository.save(createRoom(3000, true, 102, hotel4, RoomSpec.Double));
        roomRepository.save(createRoom(4000, true, 201, hotel4, RoomSpec.Family));

        roomRepository.save(createRoom( 1800, true, 101, hotel5, RoomSpec.Single));
        roomRepository.save(createRoom( 2500, true, 102, hotel5, RoomSpec.Double));
        roomRepository.save(createRoom( 4500, false, 201, hotel5, RoomSpec.Family));

        roomRepository.save(createRoom( 3500, true, 101, hotel6, RoomSpec.Single));
        roomRepository.save(createRoom(5000, true, 102, hotel6, RoomSpec.Double));
        roomRepository.save(createRoom( 8000, false, 201, hotel6, RoomSpec.Family));

        roomRepository.save(createRoom( 1500, true, 101, hotel7, RoomSpec.Single));
        roomRepository.save(createRoom( 3000, true, 102, hotel7, RoomSpec.Double));
        roomRepository.save(createRoom( 5500, true, 201, hotel7, RoomSpec.Family));

        System.out.println("Dummy hotels and rooms data inserted into the database.");
    }

    private RoomModel createRoom( long price, boolean available, Integer roomNumber, HotelModel hotel, RoomSpec roomSpec) {
        RoomModel room = new RoomModel();
        room.setPrice(price);
        room.setAvailable(available);
        room.setRoomNumber(roomNumber);
        room.setHotel(hotel);  // Set the associated hotel
        room.setRoomType(roomSpec);  // Set the room type (Single, Double, Family)
        return room;
    }
}
