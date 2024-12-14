package com.TravelAGency.TravelAgency.User.services.authintication;

import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;

public interface UserService {
    UserDto signUpUser(signUpRequest signUpRequest);
    boolean CheckUserRegisterationByEmail(String email);
}
