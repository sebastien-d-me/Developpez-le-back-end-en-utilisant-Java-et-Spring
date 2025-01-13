package com.sebastiend.ChaTop.models.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;

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
