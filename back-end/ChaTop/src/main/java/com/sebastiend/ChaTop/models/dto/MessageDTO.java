package com.sebastiend.ChaTop.models.dto;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;

import lombok.Data;

@Data
public class MessageDTO {
    private Integer id;
    private RentalEntity rental;
    private UserEntity user;
    private String message;
    private String createdAt;
    private String updatedAt;
}
