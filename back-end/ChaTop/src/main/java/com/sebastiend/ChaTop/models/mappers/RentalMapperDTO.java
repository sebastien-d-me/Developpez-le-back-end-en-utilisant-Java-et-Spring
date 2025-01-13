package com.sebastiend.ChaTop.models.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.sebastiend.ChaTop.models.dto.MessageDTO;
import com.sebastiend.ChaTop.models.dto.RentalDTO;
import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.models.entities.RentalEntity;

public class RentalMapperDTO {
        public static RentalDTO convertDTO(RentalEntity rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPictureSrc(rental.getPictureSrc());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwnerId(rental.getOwner().getId());
        rentalDTO.setCreatedAt(rental.getCreatedAt());
        rentalDTO.setUpdatedAt(rental.getUpdatedAt());
        return rentalDTO;
    }

    public static List<RentalDTO> convertList(List<RentalEntity> rentals) {
        return rentals.stream().map(RentalMapperDTO::convertDTO).collect(Collectors.toList());
    }
}
