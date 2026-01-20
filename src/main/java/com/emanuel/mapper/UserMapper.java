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
        userDto.setStoreId(savedUser.getStore()!=null? savedUser.getStore().getId():null);
        userDto.setBranchId(savedUser.getBranch()!=null?savedUser.getBranch().getId():null);
        return userDto;
    }
    public static User toEntity(UserDto userDto) {
        User createdUser = new User();
        //createdUser.setId(userDto.getId());
        createdUser.setEmail(userDto.getEmail());
        createdUser.setFullName(userDto.getFullName());
        createdUser.setRole(userDto.getRole());
        createdUser.setCreatedAt(userDto.getCreatedAt());
        createdUser.setUpdatedAt(userDto.getUpdatedAt());
        createdUser.setLastLogin(userDto.getLastLogin());
        createdUser.setPhone(userDto.getPhone());
        createdUser.setPassword(userDto.getPassword());

        return createdUser;
    }
}
