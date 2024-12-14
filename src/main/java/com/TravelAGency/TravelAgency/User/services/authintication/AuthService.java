package com.TravelAGency.TravelAgency.User.services.authintication;


import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class AuthService implements UserService
{
    private UserRepo userRepo;

    @Autowired
    public void setter(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


//takes request info and save it to the db
    public UserDto signUpUser(signUpRequest signUpRequest)
    {
        UserModel user=new UserModel();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());

        user.setPhoneNumber(signUpRequest.getPhoneNumber());
       user.getPasswd(signUpRequest.getPasswd());
        return userRepo.save(user).getDto();
    }

    public boolean CheckUserRegisterationByEmail(String Email)
    {;
        return userRepo.findFirstByEmail(Email)!=null;
    }

}
