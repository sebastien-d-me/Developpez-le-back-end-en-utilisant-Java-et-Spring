package com.sebastiend.ChaTop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.services.JWTService;
import com.sebastiend.ChaTop.services.RentalService;
import com.sebastiend.ChaTop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Authentication", description = "All routes for the authentication.")
public class AuthenticationController {
    @Autowired
    private UserService userService;
	private JWTService jwtService;

	
	public AuthenticationController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
    @Operation(summary = "Login to the back-end", description = "Login to the back-end.", tags = { "Authentication" })
	@PostMapping("/api/auth/back-end/login")
	public Map<String, String> getToken(Authentication authentication) {
        Map<String, String> token = jwtService.generateToken(authentication);
        return token;
	}

    @Operation(summary = "Create a new user account", description = "Create a new user account.", tags = { "Authentication" })
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "400", description = "Bad request")
	})
    @PostMapping("/api/auth/register")
	public Map<String, String> registerUser(@ModelAttribute UserEntity user) {
        return userService.saveUser(user);
	}

    @Operation(summary = "Log in the user", description = "Log in the user.", tags = { "Authentication" })
    @PostMapping("/api/auth/login")
	public Map<String, String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.loginUser(email, password);
	}

    @Operation(summary = "Get the info of the current logged user", description = "Get the info of the current logged user.", tags = { "Authentication" })
    @GetMapping("/api/auth/me")
	public String getMe() {
        return "c";
	}
}