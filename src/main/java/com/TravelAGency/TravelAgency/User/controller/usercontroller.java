package com.TravelAGency.TravelAgency.User.controller;

import com.TravelAGency.TravelAgency.Event.EventDto;
import com.TravelAGency.TravelAgency.Event.EventRepository;
import com.TravelAGency.TravelAgency.Event.EventServices.EventService;
import com.TravelAGency.TravelAgency.Event.UserEvents;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.Enum.RoomSpec;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomModel;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.RoomRepository;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms.UserRoomRepo;
import com.TravelAGency.TravelAgency.Rooms.RoomsController.UserRooms.UserRooms;
import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationReponse;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationRequest;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import com.TravelAGency.TravelAgency.User.services.authintication.AuthService;
import com.TravelAGency.TravelAgency.User.services.authintication.DashboardService;
import com.TravelAGency.TravelAgency.User.util.JwtUtil;
import com.TravelAGency.TravelAgency.User.services.authintication.UserService;
import com.TravelAGency.TravelAgency.notifications_system.EventRecommendationService;
import com.TravelAGency.TravelAgency.notifications_system.NotificationService;
import com.TravelAGency.TravelAgency.notifications_system.NotificationStatisticsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class usercontroller {


    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private EventRepository userEventsRepo;
   @Autowired
   private final RoomRepository roomRepo;
    @Autowired
    private final UserRoomRepo userRoomRepo;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationStatisticsService notificationStat;
    @Autowired
    private EventService eventService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private EventRecommendationService eventRecommendationService;
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }



    public usercontroller(AuthService authService, UserRepo userRepo, AuthenticationManager authenticationManager,
                          UserService userService, NotificationService notificationService, EventRepository userEventsRepo,
                          RoomRepository roomRepo,UserRoomRepo userRoomRepo,JwtUtil jwtUtil, DashboardService dashboardService
    ,EventRecommendationService eventRecommendationService) {

        this.authService = authService;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.notificationService = notificationService;
        this.userEventsRepo = userEventsRepo;
        this.roomRepo = roomRepo;
        this.userRoomRepo = userRoomRepo;
        this.jwtUtil = jwtUtil;
        this.dashboardService = dashboardService;
        this.eventRecommendationService = eventRecommendationService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<?> signUpUser(@RequestBody signUpRequest signup) {
        if (authService.CheckUserRegisterationByEmail(signup.getEmail())) {
            return new ResponseEntity<>("A user is already registered with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto CreateUser = authService.signUpUser(signup);
        return new ResponseEntity<>(CreateUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public List<UserModel> getUsers() {
        return userRepo.findAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPasswd()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect Username or Password.", HttpStatus.UNAUTHORIZED);
        }

        // Retrieve the user from the database
        Optional<UserModel> userOptional = userRepo.findByEmail(authenticationRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        UserModel user = userOptional.get();

        // Generate the JWT token
        String jwtToken = jwtUtil.generateToken(user);

        // Create the response object
        AuthenticationReponse response = new AuthenticationReponse();
        response.setUserId(user.getId());
        response.setJwt(jwtToken);

        // Return the JWT token along with the userId
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    public void UserController(UserRepo userRepo, NotificationService notificationService,
                               UserService userService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.notificationService = notificationService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;

    }
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        // Step 1: Validate user existence
        Optional<UserModel> userOptional = userRepo.findByEmail(passwordResetRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }


        UserModel user = userOptional.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newpass =passwordEncoder.encode(passwordResetRequest.getNewPassword());
        user.setPasswd(newpass);
        userRepo.save(user);


        String notificationMessage = "Your password has been successfully reset.";
        notificationService.sendPasswordResetNotification(user, notificationMessage);

        return new ResponseEntity<>("Password reset successfully. A confirmation email has been sent.", HttpStatus.OK);
    }



    @PostMapping("/book-room")
    @Transactional
    public ResponseEntity<String> bookRoom(
            @RequestParam int userId,
            @RequestParam Long roomId,
            @RequestParam int numberOfNights,
            @RequestParam RoomSpec roomType) {


        UserModel user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }


        RoomModel room = roomRepo.findById(roomId).orElse(null);
        if (room == null) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }


        if (!room.isAvailable()) {
            return new ResponseEntity<>("Room is not available", HttpStatus.BAD_REQUEST);
        }

        if (room.getRoomType() != roomType) {
            return new ResponseEntity<>("Room type does not match the requested type", HttpStatus.BAD_REQUEST);
        }


        long totalPrice = room.getPrice() * numberOfNights;

        UserRooms booking = new UserRooms();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setPrice(totalPrice);
        booking.setnumberOfNights(numberOfNights);
        booking.setRoomNumber(room.getRoomNumber());
        booking.setRoomType(room.getRoomType());


        userRoomRepo.save(booking);


        room.setAvailable(false);
        roomRepo.save(room);

        String message = "Hello " + user.getName() + ", you have successfully booked "
                + "room number " + room.getRoomNumber() + ".";
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message);


        eventRecommendationService.sendRecommendedEvents(user, room);


        return new ResponseEntity<>("Room booked successfully! Total price: " + totalPrice, HttpStatus.OK);
    }
    @PostMapping("/book-event")
    public ResponseEntity<String> bookEvent(@RequestParam Integer userId,
                                            @RequestParam String eventName,
                                            @RequestParam int numberOfTickets) {
        // Check if the user exists
        Optional<UserModel> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserModel user = userOpt.get();

        // Fetch events from the API
        List<EventDto> events = eventService.fetchEventsFromApi();
        EventDto eventToBook = events.stream()
                .filter(event -> event.getEventName().equalsIgnoreCase(eventName))
                .findFirst()
                .orElse(null);

        if (eventToBook == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        // Check if the requested number of tickets is available
        if (eventToBook.getTickets() < numberOfTickets) {
            return new ResponseEntity<>("Not enough tickets available for the event: " + eventName, HttpStatus.BAD_REQUEST);
        }

        // Decrease the ticket count by the number of tickets requested
        eventToBook.setTickets(eventToBook.getTickets() - numberOfTickets);
        System.out.println("Updated ticket count for event: " + eventName + " to " + eventToBook.getTickets());

        // Create a new UserEvents entity to save the booking
        UserEvents userEvent = new UserEvents();
        userEvent.setUser(user); // Set the user who is booking the event
        userEvent.setEventName(eventToBook.getEventName());
        userEvent.setEvent_type(eventToBook.getEventType());
        userEvent.setAddress(eventToBook.getAddress());
        userEvent.setTickets(numberOfTickets); // Set the number of tickets booked

        // Save the booking in the UserEvents table
        userEventsRepo.save(userEvent);

        // Send an SMS notification to the user
        String message = "Hello " + user.getName() + ", you have successfully booked " + numberOfTickets
                + " tickets for the event: " + eventName + ". Remaining tickets: " + eventToBook.getTickets();
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message);

        return new ResponseEntity<>("Event booked successfully and SMS sent", HttpStatus.OK);
    }



    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ResponseEntity<?> getDashboard(@RequestParam String userEmail) {


        UserModel user = userRepo.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }


        Map<String, Object> dashboard = dashboardService.getUserBookings(user);

        return new ResponseEntity<>(dashboard, HttpStatus.OK);
    }




}



