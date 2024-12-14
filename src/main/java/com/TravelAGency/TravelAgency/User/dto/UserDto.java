package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;

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

    private String passwd;
    private String email;
    private int phoneNumber;
    private Long id;

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
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
}
