package com.emanuel.payload.response;

import com.emanuel.payload.dto.UserDto;
import lombok.Data;
//Define o que o backend devolve ao frontend
import java.security.SecureRandom;
@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto  user;

}
