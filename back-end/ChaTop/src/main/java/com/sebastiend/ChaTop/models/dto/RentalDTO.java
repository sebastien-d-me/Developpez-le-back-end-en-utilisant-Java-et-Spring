package com.sebastiend.ChaTop.models.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;

import lombok.Data;

@Data
public class RentalDTO {
    private Integer id;
    private String name;
    private String surface;
    private String price;
    private String pictureSrc;
    private String description;
}
