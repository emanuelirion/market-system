package com.emanuel.mapper;

import com.emanuel.modal.User;
import com.emanuel.payload.dto.UserDto;
//converte o usuário do banco para um formato seguro e organizado para enviar ao frontend
public class UserMapper {


    public static UserDto toDTO(User savedUser) {
        UserDto userDto = new UserDto();

        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setPhone(savedUser.getPhone());
        return userDto;
    }
}
