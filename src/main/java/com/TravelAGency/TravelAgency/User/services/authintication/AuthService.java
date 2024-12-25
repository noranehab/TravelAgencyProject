package com.TravelAGency.TravelAgency.User.services.authintication;

import java.util.Optional;

import com.TravelAGency.TravelAgency.Event.EventModel;
import com.TravelAGency.TravelAgency.User.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService implements UserService
{


    private final UserRepo userRepository;
    private UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private RestTemplate restTemplate;
    @Value("${event.api.url}")
    private String apiUrl;
    @Autowired
    public AuthService(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil1) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil1;
        this.restTemplate = new RestTemplate();

    }

    @Autowired
    public void setter(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public EventModel[] fetchEvents() {
        try {
            return restTemplate.getForObject(apiUrl, EventModel[].class);
        } catch (Exception e) {
            System.err.println("Error fetching events: " + e.getMessage());
            return new EventModel[0]; // Return an empty array if there's an error
        }
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
        return username -> {
            Optional<UserModel> userOptional = Optional.ofNullable(userRepository.findFirstByEmail(username));
            return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        };
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}