package com.TravelAGency.TravelAgency.User.services.authintication;

import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService {
    UserDto signUpUser(signUpRequest signUpRequest);
    boolean CheckUserRegisterationByEmail(String email);
    UserDetailsService userDetailsService();
}
