package com.sebastiend.ChaTop.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.services.RentalService;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping("/rental/{id}")
    public Optional<RentalEntity> getRental(@PathVariable Long id) {
        return rentalService.getRental(id);
    }

    @GetMapping("/rentals")
    public Iterable<RentalEntity> getRentals() {
        return rentalService.getRentals();
    }

    @PostMapping("/rentals/create")
    public RentalEntity createRental(@ModelAttribute RentalEntity rental) {
        
        RentalEntity newRental = new RentalEntity();
        // Mettre le code dans le service)
        newRental.setName(rental.getName());
        newRental.setSurface(rental.getSurface());
        newRental.setPrice(rental.getPrice());
        /*System.out.println(file.getOriginalFilename());*/
        newRental.setDescription(rental.getDescription());
        newRental.setOwnerId(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newRental.setCreatedAt(dateFormatter.format(currentDate));
        newRental.setUpdatedAt(dateFormatter.format(currentDate));
        // Mettre le code dans le service)
        return rentalService.saveRental(newRental);
    }
}
