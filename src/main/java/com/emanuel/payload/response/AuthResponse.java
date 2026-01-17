package com.emanuel.payload.response;

import com.emanuel.payload.dto.UserDto;
import lombok.Data;

import java.security.SecureRandom;
@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto  user;

}
