package com.sebastiend.ChaTop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.dto.RentalCreateDTO;
import com.sebastiend.ChaTop.models.dto.RentalDTO;
import com.sebastiend.ChaTop.models.dto.RentalUpdateDTO;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.models.mappers.RentalMapperDTO;
import com.sebastiend.ChaTop.repositories.RentalRepository;
import com.sebastiend.ChaTop.repositories.UserRepository;

import lombok.Data;

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
        RentalEntity newRental = new RentalEntity();
        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setDescription(rental.getDescription());
        String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(jwt);
        if(user == null) {
            return Map.of("message", "The user not exist");
        }
        newRental.setOwner(user);
        String uniqueID = UUID.randomUUID().toString().substring(0, 15);
        byte[] bytes = picture.getBytes();
        Path path = Paths.get(rentalsUploadsDirectory + uniqueID+"__"+picture.getOriginalFilename());
        Files.write(path, bytes);
        newRental.setPictureSrc(uniqueID+"-"+picture.getOriginalFilename());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newRental.setCreatedAt(dateFormatter.format(currentDate));
        newRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(newRental);
        return Map.of("message", "Rental created !");
    }

    public Map<String, String> editRental(final Integer id, String name, String surface, String price, MultipartFile picture, String description, RentalUpdateDTO rental) throws IOException {
        RentalEntity existRental = rentalRepository.findById(id).get();
        if(existRental == null) {
            return Map.of("message", "The rental not exist");
        }
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
        if(name != null) {
            existRental.setName(rental.getName());
        }
        
        existRental.setOwner(existRental.getOwner());
        if(picture != null) {
            String uniqueID = UUID.randomUUID().toString().substring(0, 15);
            byte[] bytes = picture.getBytes();
            Path path = Paths.get(rentalsUploadsDirectory + uniqueID+"__"+picture.getOriginalFilename());
            Files.write(path, bytes);
            existRental.setPictureSrc(uniqueID+"-"+picture.getOriginalFilename());
        }
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        existRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(existRental);
        return Map.of("message", "Rental edited !");
    }
}
