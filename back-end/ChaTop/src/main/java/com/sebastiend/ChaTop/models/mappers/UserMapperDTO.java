package com.sebastiend.ChaTop.models.mappers;


import com.sebastiend.ChaTop.models.dto.Users.UserDTO;
import com.sebastiend.ChaTop.models.dto.Users.UserRegisterDTO;
import com.sebastiend.ChaTop.models.entities.UserEntity;


public class UserMapperDTO {
    public static UserDTO convertDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    public static UserEntity convertRegisterEntity(UserRegisterDTO userRegister) {
        UserEntity user = new UserEntity();
        user.setName((userRegister.getName()));
        user.setEmail((userRegister.getEmail()));
        user.setPassword((userRegister.getPassword()));
        return user;
    }
}