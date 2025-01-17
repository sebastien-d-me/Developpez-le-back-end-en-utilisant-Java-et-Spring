package com.sebastiend.ChaTop.models.dto.Users;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;

    @JsonProperty("created_at")
    private String createdAt;
    
    @JsonProperty("updated_at")
    private String updatedAt;
}
