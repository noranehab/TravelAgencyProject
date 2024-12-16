package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;

@Data
public class AuthenticationReponse
{
    private Long userId;

    private String jwt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


}
