package com.sebastiend.ChaTop.models.dto.Users;


import lombok.Data;


@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
}
