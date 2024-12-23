package com.sebastiend.ChaTop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public RentalEntity createRental(@ModelAttribute RentalEntity rental, @RequestParam("picture") MultipartFile picture) throws IOException {
        return rentalService.saveRental(rental, picture);
    }

    @DeleteMapping("/rental/{id}/delete")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }
}
