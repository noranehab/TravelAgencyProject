package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;

@Data
public class AuthenticationRequest
{
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private String passwd;
}
