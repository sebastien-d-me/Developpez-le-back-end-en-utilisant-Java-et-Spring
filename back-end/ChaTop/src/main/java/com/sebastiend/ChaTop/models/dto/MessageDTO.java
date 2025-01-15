package com.sebastiend.ChaTop.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class MessageDTO {
    @JsonProperty("rental_id")
    private Integer rental;
    @JsonProperty("user_id")
    private Integer user;
    private String message;
}
