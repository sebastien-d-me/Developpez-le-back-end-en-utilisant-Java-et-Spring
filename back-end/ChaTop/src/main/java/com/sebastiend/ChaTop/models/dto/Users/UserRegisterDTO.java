package com.sebastiend.ChaTop.models.dto.Users;


import lombok.Data;


@Data
public class UserRegisterDTO {
    private String name;
    private String email;
    private String password;
}
