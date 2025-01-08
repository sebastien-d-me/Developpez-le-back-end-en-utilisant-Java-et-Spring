package com.sebastiend.ChaTop.controllers;

import java.io.IOException;
import java.util.Map;
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
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.services.RentalService;
import com.sebastiend.ChaTop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "Rentals", description = "All routes for the rentals.")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    private UserService userService;

    @Operation(summary = "Get all the rentals", description = "Get all the rentals.", tags = { "Rentals" })
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    @GetMapping("/api/rentals")
    public Iterable<RentalEntity> getRentals() {
        return rentalService.getRentals();
    }

    @Operation(summary = "Get a specific rental", description = "Get a specific rental.", tags = { "Rentals" })
    @GetMapping("/api/rentals/{id}")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    public Optional<RentalEntity> getRental(@PathVariable Long id) {
        return rentalService.getRental(id);
    }

    @Operation(summary = "Create a rental", description = "Create a rental.", tags = { "Rentals" })
    @PostMapping("/api/rentals")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    public Map<String, String> createRental(@ModelAttribute RentalEntity rental, @RequestParam("picture") MultipartFile picture) throws IOException {
        return rentalService.saveRental(rental, picture);
    }

    @Operation(summary = "Edit a rental", description = "Edit a rental.", tags = { "Rentals" })
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    @PutMapping("/api/rental/{id}")
    public Map<String, String> editRental(@PathVariable Long id, @ModelAttribute RentalEntity rental) throws IOException {
        return rentalService.editRental(id, rental);
    }
}
