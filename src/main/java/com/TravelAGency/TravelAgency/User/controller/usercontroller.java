package com.TravelAGency.TravelAgency.User.controller;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationReponse;
import com.TravelAGency.TravelAgency.User.dto.AuthenticationRequest;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
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
    private UserService authService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private final AuthenticationManager authenticationManager;

    //private final JwtUil jwtUil;
    public usercontroller(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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

    public AuthenticationReponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPasswd()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or passwd.");
        }
        final UserDetails userDetails =null;
        Optional<UserModel> optionalUser = Optional.ofNullable(userRepo.findFirstByEmail(userDetails.getUsername()));
        final String jwt= JwtUtil.generateToken(userDetails);

        AuthenticationReponse authenticationReponse =new AuthenticationReponse();
        if(optionalUser.isPresent()) {
            authenticationReponse.setJwt(jwt);
            authenticationReponse.setUserId(optionalUser.get().getId());
        }
        return authenticationReponse;



    }
}