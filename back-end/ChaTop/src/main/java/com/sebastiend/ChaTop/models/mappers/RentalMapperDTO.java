package com.sebastiend.ChaTop.models.mappers;


import com.sebastiend.ChaTop.models.dto.Rentals.RentalDTO;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


public class RentalMapperDTO {
    @Value("${rentals.link.directory}")
    private String rentalsLinkDirectory;

    public static RentalDTO convertDTO(RentalEntity rental, String rentalsLinkDirectory) {
        String hostURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPictureSrc(hostURL + rentalsLinkDirectory + rental.getPictureSrc());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwnerId(rental.getOwner().getId());
        rentalDTO.setCreatedAt(rental.getCreatedAt());
        rentalDTO.setUpdatedAt(rental.getUpdatedAt());
        return rentalDTO;
    }

    public static List<RentalDTO> convertList(List<RentalEntity> rentals, String rentalsLinkDirectory) {
        return rentals.stream().map(rental -> convertDTO(rental, rentalsLinkDirectory)).collect(Collectors.toList());
    }
}
