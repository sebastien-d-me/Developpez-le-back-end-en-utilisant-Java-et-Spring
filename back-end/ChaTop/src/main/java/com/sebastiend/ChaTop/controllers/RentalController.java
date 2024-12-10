package com.sebastiend.ChaTop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.services.RentalService;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping("/rentals")
    public Iterable<RentalEntity> getRentals() {
        return rentalService.getRentals();
    }
}
