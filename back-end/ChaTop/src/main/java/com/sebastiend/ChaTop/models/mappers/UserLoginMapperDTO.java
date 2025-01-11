package com.sebastiend.ChaTop.models.mappers;

import com.sebastiend.ChaTop.models.dto.UserDTO;
import com.sebastiend.ChaTop.models.dto.UserLoginDTO;
import com.sebastiend.ChaTop.models.dto.UserRegisterDTO;
import com.sebastiend.ChaTop.models.entities.UserEntity;

public class UserLoginMapperDTO {
    public static UserEntity convertEntity(UserLoginDTO userLogin) {
        UserEntity user = new UserEntity();
        user.setEmail(userLogin.getEmail());
        user.setPassword(userLogin.getPassword());
        return user;
    }
}
