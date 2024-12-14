package com.TravelAGency.TravelAgency.User.controller;

import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import com.TravelAGency.TravelAgency.User.services.authintication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class displaycontroller {

    @Autowired
    private UserService authService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
public ResponseEntity<?> signUpUser(@RequestBody signUpRequest signup) {
    if (authService.CheckUserRegisterationByEmail(signup.getEmail())) {
        return new ResponseEntity<>("A user is already registered with this email", HttpStatus.NOT_ACCEPTABLE);
    }
    UserDto CreateUser = authService.signUpUser(signup);
    return new ResponseEntity<>(CreateUser, HttpStatus.CREATED);
}

    @RequestMapping(value = "/display", method = RequestMethod.GET)
public List<UserModel> getUsers()
{
    return userRepo.findAll();
}

}
