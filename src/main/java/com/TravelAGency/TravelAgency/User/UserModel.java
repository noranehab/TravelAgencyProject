package com.TravelAGency.TravelAgency.User;

import com.TravelAGency.TravelAgency.Event.EventModel;
import com.TravelAGency.TravelAgency.Event.UserEvents;
import com.TravelAGency.TravelAgency.User.dto.UserDto;
import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name="Users")
@Component
@Scope("prototype")
public class UserModel implements UserDetails {
   private String name;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserEvents> userEvents;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<HotelModel> hotelBookings;

    public String getName() {
        return name;
    }

   public String retrievePassword(String passwd) {
        return this.passwd;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
    }

    private String passwd;
    private String email;
    private int phoneNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

   // @Column(name = "passwd", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generating pk
    private Long id;

    public List<HotelModel> getHotelBookings() {
        return hotelBookings;
    }

    // Getter for eventBookings
    public List<UserEvents> getEventBookings() {
        return userEvents;
    }


public UserDto getDto()
{
    UserDto userdto=new UserDto();
    userdto.setId(id);
    userdto.setPasswd(passwd);
    userdto.setEmail(email);
    userdto.setName(name);

    userdto.setPhoneNumber(phoneNumber);
return userdto;
}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return passwd;
    }



    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
