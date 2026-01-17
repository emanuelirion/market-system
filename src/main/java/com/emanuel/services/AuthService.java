package com.emanuel.services;


import com.emanuel.exception.UserException;
import com.emanuel.payload.dto.UserDto;
import com.emanuel.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto);


}
