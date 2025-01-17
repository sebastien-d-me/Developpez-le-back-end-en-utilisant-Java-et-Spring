package com.sebastiend.ChaTop.models.dto.Rentals;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class RentalDTO {
    private Integer id;
    private String name;
    private Integer surface;
    private Integer price;

    @JsonProperty("picture")
    private String pictureSrc;

    private String description;

    @JsonProperty("owner_id")
    private Integer ownerId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
