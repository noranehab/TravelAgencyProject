package com.TravelAGency.TravelAgency.User.dto;

import lombok.Data;

@Data
public class AuthenticationRequest
{
    private String email;
    private String passwd;
}
