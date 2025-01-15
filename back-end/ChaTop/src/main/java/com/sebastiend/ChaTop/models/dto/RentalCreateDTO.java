package com.sebastiend.ChaTop.models.dto;


import lombok.Data;


@Data
public class RentalCreateDTO {
    private String name;
    private Integer surface;
    private Integer price;
    private String pictureSrc;
    private String description;
    private Integer ownerId;
}
