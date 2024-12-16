package com.TravelAGency.TravelAgency.User.services.authintication;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.TravelAGency.TravelAgency.User.UserModel;
import com.TravelAGency.TravelAgency.User.UserRepo;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.User.dto.signUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserService
{
    private final UserRepo userRepository;
    private UserRepo userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
    public AuthService(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
}

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
        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequest.retrievePassword());
        user.setPasswd(encodedPassword);

        return userRepo.save(user).getDto();
    }

    public boolean CheckUserRegisterationByEmail(String Email)
    {;
        return userRepo.findFirstByEmail(Email)!=null;
    }

    public UserDetailsService userDetailsService(){
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<UserModel> userOptional = Optional.ofNullable(userRepository.findFirstByEmail(username));
            return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        }
    };
    }

}
