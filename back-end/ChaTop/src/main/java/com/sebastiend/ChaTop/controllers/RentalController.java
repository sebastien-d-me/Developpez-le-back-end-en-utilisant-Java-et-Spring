package com.sebastiend.ChaTop.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.services.RentalService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public RentalEntity createRental(@ModelAttribute RentalEntity rental, @RequestParam("picture") MultipartFile picture, @RequestParam("owner_id") Integer owner) throws IOException {
        return rentalService.saveRental(rental, picture, owner);
    }

    @PutMapping("/rental/{id}/edit")
    public RentalEntity editRental(@PathVariable Long id, @ModelAttribute RentalEntity rental) throws IOException {
        return rentalService.editRental(id, rental);
    }
}
