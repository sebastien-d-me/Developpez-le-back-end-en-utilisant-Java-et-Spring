package com.sebastiend.ChaTop.controllers;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.services.JWTService;


@RestController
public class LoginController {
	private JWTService jwtService;
	
	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
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