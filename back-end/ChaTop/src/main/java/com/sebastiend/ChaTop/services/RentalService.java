package com.sebastiend.ChaTop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.repositories.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Optional<RentalEntity> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Iterable<RentalEntity> getRentals() {
        return rentalRepository.findAll();
    }

    public Map<String, String> saveRental(RentalEntity rental, MultipartFile picture, Integer owner) throws IOException {
        RentalEntity newRental = new RentalEntity();
        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        newRental.setDescription(rental.getDescription());
        newRental.setOwnerId(owner);
        String uniqueID = UUID.randomUUID().toString().substring(0, 15);
        byte[] bytes = picture.getBytes();
        Path path = Paths.get("src/main/resources/uploads/rentals/" + uniqueID+"__"+picture.getOriginalFilename());
        Files.write(path, bytes);
        newRental.setPictureSrc(uniqueID+"-"+picture.getOriginalFilename());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newRental.setCreatedAt(dateFormatter.format(currentDate));
        newRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(newRental);
        return Map.of("message", "Rental created !");
    }

    public Map<String, String> editRental(final Long id, RentalEntity rental) throws IOException {
        RentalEntity newRental = new RentalEntity();
        RentalEntity existRental = getRental(id).orElse(newRental);
        existRental.setName(rental.getName());
        existRental.setSurface(rental.getSurface());
        existRental.setPrice(rental.getPrice());
        existRental.setDescription(rental.getDescription());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        existRental.setUpdatedAt(dateFormatter.format(currentDate));
        rentalRepository.save(existRental);
        return Map.of("message", "Rental edited !");
    }
}
