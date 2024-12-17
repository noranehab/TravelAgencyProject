package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class signUpRequest {
    private String name;
    private String email;
    private Integer phoneNumber;
    private Long id;
    private String passwd;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
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

    public String retrievePassword() {
        return passwd;
    }




}
