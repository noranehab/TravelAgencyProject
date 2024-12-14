package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;

@Data
public class AuthenticationReponse
{
    private String jwt;
    private Long userId;

}
