package com.sebastiend.ChaTop.controllers;


import com.sebastiend.ChaTop.models.dto.Users.UserDTO;
import com.sebastiend.ChaTop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Users", description = "All routes for the users.")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{\n"+
                    "  \"id\": 1,\n"+
                    "  \"name\": \"test TEST\",\n"+
                    "  \"email\": \"test@testa.com\",\n"+
                    "  \"createdAt\": \"2025-01-13 20:36:38\",\n"+
                    "  \"updatedAt\": \"2025-01-13 20:36:38\"\n"+
                "}"
            ),
            mediaType = "application/json"  
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = ""
            ),
            mediaType = "application/json"
            
        )),
	})
    @GetMapping("/api/users/{id}")
    @Operation(summary = "Get a specific user", description = "Get a specific user.", tags = { "Users" })
    @SecurityRequirement(name = "bearerAuth")
    public Optional<UserDTO> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }
}
