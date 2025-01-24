package com.sebastiend.ChaTop.services;


import com.sebastiend.ChaTop.models.dto.Message.MessageResponseDTO;
import com.sebastiend.ChaTop.models.dto.Rentals.*;
import com.sebastiend.ChaTop.models.entities.*;
import com.sebastiend.ChaTop.models.mappers.RentalMapperDTO;
import com.sebastiend.ChaTop.repositories.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;


    @Autowired
    private UserRepository userRepository;


    private JwtDecoder jwtDecoder;


    @Value("${rentals.uploads.directory}")
    private String rentalsUploadsDirectory;

    @Value("${rentals.link.directory}")
    private String rentalsLinkDirectory;

    public Optional<RentalDTO> getRental(final Integer id) {
        return rentalRepository.findById(id).map(rental -> RentalMapperDTO.convertDTO(rental, rentalsLinkDirectory));
    }


    public RentalListResponseDTO getRentals() {
        List<RentalDTO> rentals = rentalRepository.findAll().stream().map(rental -> RentalMapperDTO.convertDTO(rental, rentalsLinkDirectory)).collect(Collectors.toList());
        return new RentalListResponseDTO(rentals);
    }


    public RentalResponseDTO saveRental(String name, String surface, String price, MultipartFile picture, String description, RentalCreateDTO rental) throws IOException {
        if(name == null || surface == null || price == null || picture.getOriginalFilename() == "" || description == null) {
            throw new IllegalArgumentException("Some fields are empty.");
        }

        String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userCheckExist = userRepository.findByEmail(jwt);
        if(userCheckExist == null) {
            throw new IllegalArgumentException("The user not exist");
        }

        String uniqueID = UUID.randomUUID().toString().substring(0, 15);
        byte[] bytes = picture.getBytes();
        Path path = Paths.get(rentalsUploadsDirectory + uniqueID+"__"+picture.getOriginalFilename());
        Files.write(path, bytes);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        RentalEntity newRental = new RentalEntity();
        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setDescription(rental.getDescription());
        newRental.setOwner(userCheckExist);
        newRental.setPictureSrc(uniqueID+"__"+picture.getOriginalFilename());
        newRental.setCreatedAt(dateFormatter.format(currentDate));
        newRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(newRental);
        return new RentalResponseDTO("Rental created !");  
    }


    public RentalResponseDTO editRental(final Integer id, String name, String surface, String price, MultipartFile picture, String description, RentalUpdateDTO rental) throws IOException {
        Optional<RentalEntity> rentalCheckExist = rentalRepository.findById(id);

        if(!rentalCheckExist.isPresent()) {
            throw new IllegalArgumentException("The rental not exist");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        RentalEntity existRental = rentalRepository.findById(id).get();
        if(name != null) {
            existRental.setName(rental.getName());
        }
        if(surface != null) {
            existRental.setSurface(rental.getSurface());
        }
        if(price != null) {
            existRental.setPrice(rental.getPrice());
        }
        if(description != null) {
            existRental.setDescription(rental.getDescription());
        }
        if(name != null) {
            existRental.setName(rental.getName());
        }
        if(picture != null) {
            String uniqueID = UUID.randomUUID().toString().substring(0, 15);
            byte[] bytes = picture.getBytes();
            Path path = Paths.get(rentalsUploadsDirectory + uniqueID+"__"+picture.getOriginalFilename());
            Files.write(path, bytes);
            existRental.setPictureSrc(uniqueID+"__"+picture.getOriginalFilename());
        }
        existRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(existRental);
        return new RentalResponseDTO("Rental edited !"); 
    }
}
