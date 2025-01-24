package com.sebastiend.ChaTop.models.dto.Rentals;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RentalListResponseDTO {
    @JsonProperty("rentals")
    private List<RentalDTO> rentals;
}
