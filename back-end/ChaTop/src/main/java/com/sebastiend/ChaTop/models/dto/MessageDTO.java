package com.sebastiend.ChaTop.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;

import lombok.Data;

@Data
public class MessageDTO {
    @JsonProperty("rental_id")
    private Integer rental;
    @JsonProperty("user_id")
    private Integer user;
    private String message;
}
