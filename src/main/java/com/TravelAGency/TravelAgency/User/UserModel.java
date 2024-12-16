package com.TravelAGency.TravelAgency.User;

import com.TravelAGency.TravelAgency.User.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name="Users")
@Component
@Scope("prototype")
public class UserModel implements UserDetails {
   private String name;

    public String getName() {
        return name;
    }

    public String getPasswd(String passwd) {
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

    @Column(name = "passwd", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generating pk
    private Long id;

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
