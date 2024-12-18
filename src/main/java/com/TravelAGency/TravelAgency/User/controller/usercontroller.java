package com.TravelAGency.TravelAgency.User.controller;

import com.TravelAGency.TravelAgency.Event.EventModel;
import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationRequest;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import com.TravelAGency.TravelAgency.User.services.authintication.AuthService;
import com.TravelAGency.TravelAgency.User.services.authintication.UserService;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.TravelAGency.TravelAgency.notifications_system.NotificationService;
import com.TravelAGency.TravelAgency.notifications_system.PasswordRest_Notification.PasswordResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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



    public usercontroller(AuthService authService, UserRepo userRepo, AuthenticationManager authenticationManager, UserService userService, NotificationService notificationService) {

        this.authService = authService;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.notificationService = notificationService;
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
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            // Attempt to authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPasswd()));
        } catch (BadCredentialsException e) {
            // If authentication fails, return error message
            return new ResponseEntity<>("Incorrect Username or Password.", HttpStatus.UNAUTHORIZED);
        }

        // If authentication is successful, return a success message
        return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
    }
    @Autowired
    private NotificationService notificationService;



    public void UserController(UserRepo userRepo, NotificationService notificationService,
                               UserService userService, AuthenticationManager authenticationManager,
                               AuthService authService) {
        this.userRepo = userRepo;
        this.notificationService = notificationService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        // Step 1: Validate user existence
        Optional<UserModel> userOptional = userRepo.findByEmail(passwordResetRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Step 2: Update the user's password
        UserModel user = userOptional.get();
        user.setPasswd(passwordResetRequest.getNewPassword()); // Ideally, hash the password before saving
        userRepo.save(user);

        // Step 3: Send notification to the user
        String notificationMessage = "Your password has been successfully reset.";
        notificationService.sendPasswordResetNotification(user, notificationMessage);

        return new ResponseEntity<>("Password reset successfully. A confirmation email has been sent.", HttpStatus.OK);
    }

    // sending Notification for booking

    @PostMapping("/book-hotel")
    public ResponseEntity<String> bookHotel(@RequestParam Long userId, @RequestParam String hotelName) {
        // Fetch user by ID
        Optional<UserModel> userOpt = userRepo.findById(Math.toIntExact(userId));
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserModel user = userOpt.get();

        // Add hotel booking (you need to implement the addHotelBooking method in UserModel)
        HotelModel hotel = new HotelModel();
        hotel.setHotelName(hotelName);
        user.getHotelBookings().add(hotel);
        userRepo.save(user);

        // Send SMS notification
        String message = "Hello " + user.getName() + ", you have successfully booked the hotel: " + hotelName;
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message);

        return new ResponseEntity<>("Hotel booked successfully and SMS sent", HttpStatus.OK);
    }

    @PostMapping("/book-event")
    public ResponseEntity<String> bookEvent(@RequestParam Long userId, @RequestParam String eventName) {
        // Fetch user by ID
        Optional<UserModel> userOpt = userRepo.findById(Math.toIntExact(userId));
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserModel user = userOpt.get();

        // Add event booking (you need to implement the addEventBooking method in UserModel)
        EventModel event = new EventModel();
        event.setEventName(eventName);
        user.getEventBookings().add(event);
        userRepo.save(user);

        // Send SMS notification
        String message = "Hello " + user.getName() + ", you have successfully booked the event: " + eventName;
        notificationService.sendSmsNotification(String.valueOf(user.getPhoneNumber()), message);

        return new ResponseEntity<>("Event booked successfully and SMS sent", HttpStatus.OK);
    }



    // Endpoint to request a password reset
//    @RequestMapping(value = "/reset-password-request", method = RequestMethod.POST)
//    public ResponseEntity<String> resetPasswordRequest(@RequestBody String email) {
//        Optional<UserModel> userOptional = Optional.ofNullable(userRepo.findFirstByEmail(email));
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>("No user found with the provided email.", HttpStatus.NOT_FOUND);
//        }
//
//        // Generate a password reset token (could be a UUID or secure token)
//        String resetToken = generateResetToken();
//
//        // Send notification (SMS or Email)
//        notificationService.sendPasswordResetNotification(userOptional.get(), resetToken);
//
//        return new ResponseEntity<>("Password reset request received. Check your email for further instructions.", HttpStatus.OK);
//    }
//
//    // Method to generate a secure password reset token
//    private String generateResetToken() {
//        return UUID.randomUUID().toString();  // Example of a random token generator
//    }
//
//
//    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
//    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest resetRequest) {
//        // Find the user by email
//        Optional<UserModel> userOptional = Optional.ofNullable(userRepo.findFirstByEmail(resetRequest.getEmail()));
//        if (!userOptional.isPresent()) {
//            // If no user is found, return a 404 response
//            return new ResponseEntity<>("No user found with the provided email.", HttpStatus.NOT_FOUND);
//        }
//
//        // Get the user entity
//        UserModel user = userOptional.get();
//
//        // Set the new password (not encoded as per your request)
//        user.setPasswd(resetRequest.getNewPassword());
//        // Save the updated user to the repository
//        userRepo.save(user);
//
//        // Send a confirmation notification (dummy confirmation message for simplicity)
//        String confirmationMessage = "Your password has been successfully reset.";
//        notificationService.sendPasswordResetNotification(user, confirmationMessage);
//
//        // Return a response indicating the password reset was successful
//        return new ResponseEntity<>("Password reset successful. Confirmation sent to user.", HttpStatus.OK);
//    }


}




