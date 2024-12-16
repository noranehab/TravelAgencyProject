package com.TravelAGency.TravelAgency.User.controller;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationReponse;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationRequest;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import com.TravelAGency.TravelAgency.User.services.authintication.AuthService;
import com.TravelAGency.TravelAgency.User.services.authintication.UserService;
import com.TravelAGency.TravelAgency.User.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class usercontroller {

    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService userService;



    public usercontroller(JwtUtil jwtUtil, AuthService authService, UserRepo userRepo, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
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
    public AuthenticationReponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPasswd()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or passwd.");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail()); // Fetch the UserDetails from your service or repo
        Optional<UserModel> optionalUser = Optional.ofNullable(userRepo.findFirstByEmail(userDetails.getUsername()));

        // Use the public method to generate the JWT
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationReponse authenticationReponse = new AuthenticationReponse();
        if (optionalUser.isPresent()) {
            authenticationReponse.setJwt(jwt);
            authenticationReponse.setUserId(optionalUser.get().getId());
        }
        return authenticationReponse;
    }


}