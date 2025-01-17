package com.sebastiend.ChaTop.services;


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


    public Optional<RentalDTO> getRental(final Integer id) {
        return rentalRepository.findById(id).map(RentalMapperDTO::convertDTO);
    }


    public List<RentalDTO> getRentals() {
        return rentalRepository.findAll().stream().map(RentalMapperDTO::convertDTO).collect(Collectors.toList());
    }


    public Map<String, String> saveRental(String name, String surface, String price, MultipartFile picture, String description, RentalCreateDTO rental) throws IOException {
        if(name == null || surface == null || price == null || picture.getOriginalFilename() == "" || description == null) {
            return Map.of("message", "Some fields are empty.");
        }

        String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userCheckExist = userRepository.findByEmail(jwt);
        if(userCheckExist == null) {
            return Map.of("message", "The user not exist");
        }

        String uniqueID = UUID.randomUUID().toString().substring(0, 15);
        byte[] bytes = picture.getBytes();
        Path path = Paths.get(rentalsUploadsDirectory + uniqueID+"__"+picture.getOriginalFilename());
        Files.write(path, bytes);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();

        RentalEntity newRental = new RentalEntity();
        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setDescription(rental.getDescription());
        newRental.setOwner(userCheckExist);
        newRental.setPictureSrc(uniqueID+"-"+picture.getOriginalFilename());
        newRental.setCreatedAt(dateFormatter.format(currentDate));
        newRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(newRental);  
        return Map.of("message", "Rental created !");
    }


    public Map<String, String> editRental(final Integer id, String name, String surface, String price, MultipartFile picture, String description, RentalUpdateDTO rental) throws IOException {
        Optional<RentalEntity> rentalCheckExist = rentalRepository.findById(id);

        if(!rentalCheckExist.isPresent()) {
            return Map.of("message", "The rental not exist");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
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
            existRental.setPictureSrc(uniqueID+"-"+picture.getOriginalFilename());
        }
        existRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(existRental);
        return Map.of("message", "Rental edited !");
    }
}
