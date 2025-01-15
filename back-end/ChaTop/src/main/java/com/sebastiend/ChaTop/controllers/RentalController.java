package com.sebastiend.ChaTop.controllers;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.dto.Rentals.RentalCreateDTO;
import com.sebastiend.ChaTop.models.dto.Rentals.RentalDTO;
import com.sebastiend.ChaTop.models.dto.Rentals.RentalUpdateDTO;
import com.sebastiend.ChaTop.services.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@Tag(name = "Rentals", description = "All routes for the rentals.")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Get all the rentals", description = "Get all the rentals.", tags = { "Rentals" })
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = 
                "[\n"+
                    "{\n"+
                        "  \"id\": 1,\n"+
                        "  \"name\": \"name edited\",\n"+
                        "  \"surface\": 50,\n"+
                        "  \"price\": 200,\n"+
                        "  \"pictureSrc\": \"1705ceab-7493-4-Screenshot_3.png\",\n"+
                        "  \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n"+
                        "  \"ownerId\": 1,\n"+
                        "  \"createdAt\": \"2025-01-13 20:36:58\",\n"+
                        "  \"updatedAt\": \"2025-01-13 20:37:37\"\n"+
                    "},"+
                    "{\n"+
                        "  \"id\": 2,\n"+
                        "  \"name\": \"name 2\",\n" +
                        "  \"surface\": 100,\n"+
                        "  \"price\": 50,\n"+
                        "  \"pictureSrc\": \"16b7d4af-7081-4-Screenshot_2.png\",\n"+
                        "  \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum.\",\n"+
                        "  \"ownerId\": 1,\n"+
                        "  \"createdAt\": \"2025-01-13 20:37:22\",\n"+
                        "  \"updatedAt\": \"2025-01-13 20:37:22\"\n"+
                    "}\n"+
                "]"
            )
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        ))
	})
    @GetMapping("/api/rentals")
    public List<RentalDTO> getRentals() {
        return rentalService.getRentals();
    }

    @Operation(summary = "Get a specific rental", description = "Get a specific rental.", tags = { "Rentals" })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/api/rentals/{id}")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = 
            "{\n"+
                "  \"id\": 1,\n"+
                "  \"name\": \"name edited\",\n"+
                "  \"surface\": 50,\n"+
                "  \"price\": 200,\n"+
                "  \"pictureSrc\": \"1705ceab-7493-4-Screenshot_3.png\",\n"+
                "  \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.\",\n"+
                "  \"ownerId\": 1,\n"+
                "  \"createdAt\": \"2025-01-13 20:36:58\",\n"+
                "  \"updatedAt\": \"2025-01-13 20:37:37\"\n"+
            "}"
            )
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        ))
	})
    public Optional<RentalDTO> getRental(@PathVariable Integer id) {
        return rentalService.getRental(id);
    }

    @Operation(summary = "Create a rental", description = "Create a rental.", tags = { "Rentals" })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/api/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\": \"Rental created !\"}")
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        ))
	})
    public Map<String, String> createRental(
        @RequestParam("name") String name,
        @RequestParam("surface") String surface,
        @RequestParam("price") String price,
        @RequestParam("picture") MultipartFile picture,
        @RequestParam("description") String description, @Parameter(hidden = true) RentalCreateDTO rental) throws IOException {
        return rentalService.saveRental(name, surface, price, picture, description, rental);
    }

    @Operation(summary = "Edit a rental", description = "Edit a rental.", tags = { "Rentals" })
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\": \"Rental updated !\"}")
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        ))
	})
    @PutMapping(value="/api/rentals/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> editRental(@PathVariable Integer id, @RequestParam(required = false) String name,
        @RequestParam(required = false) String surface,
        @RequestParam(required = false) String price,
        @RequestParam(required = false) MultipartFile picture,
        @RequestParam(required = false) String description, @Parameter(hidden = true) RentalUpdateDTO rental) throws IOException {
        return rentalService.editRental(id, name, surface, price, picture, description, rental);
    }
}
