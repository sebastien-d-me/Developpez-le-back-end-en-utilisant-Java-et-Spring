package com.sebastiend.ChaTop.models.dto.Rentals;


import lombok.Data;


@Data
public class RentalUpdateDTO {
    private String name;
    private Integer surface;
    private Integer price;
    private String pictureSrc;
    private String description;
    private Integer ownerId;
}
