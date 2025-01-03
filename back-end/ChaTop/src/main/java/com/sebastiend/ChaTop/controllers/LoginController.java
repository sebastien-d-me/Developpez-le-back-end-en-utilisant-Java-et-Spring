package com.sebastiend.ChaTop.controllers;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.services.JWTService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Authentication", description = "All routes for the authentication.")
public class LoginController {
	private JWTService jwtService;
	
	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
    @Operation(summary = "Login to the back-end", description = "Login to the back-end.", tags = { "Rentals" })
	@PostMapping("/api/auth/login")
	public Map<String, String> getToken(Authentication authentication) {
        Map<String, String> token = jwtService.generateToken(authentication);
        return token;
	}

    /*@GetMapping("/api/auth/me")
	public String getMe(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return token;
	}*/
}