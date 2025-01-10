package com.sebastiend.ChaTop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.dto.UserDTO;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Users", description = "All routes for the users.")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get a specific user", description = "Get a specific user.", tags = { "Users" })
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "401", description = "Unauthorized")
	})
    @GetMapping("/api/users/{id}")
    public Optional<UserDTO> getUser(@PathVariable Integer id) {
        return userService.getUser(id).map(UserMapperDTO::convertDTO);
        // ne pas mettre l'entity dans le controller, DTO plutôt
    }
    
}
