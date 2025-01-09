package com.sebastiend.ChaTop.controllers;

import java.util.Map;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.dto.UserRegisterDTO;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.services.AuthenticationService;
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
    private AuthenticationService authenticationService;
    private UserService userService;
    
    @Operation(summary = "Create a new user account", description = "Create a new user account.", tags = { "Authentication" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/api/auth/register")
    public Map<String, String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserEntity user = UserMapperDTO.convertEntity(userRegisterDTO);
        return authenticationService.saveUser(user);
    }

    @Operation(summary = "Log in the user", description = "Log in the user.", tags = { "Authentication" })
    @PostMapping("/api/auth/login")
    public Map<String, String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) throws AuthenticationException {
        return authenticationService.loginUser(email, password);
	}

    @Operation(summary = "Get the info of the current logged user", description = "Get the info of the current logged user.", tags = { "Authentication" })
    @GetMapping("/api/auth/me")
	public String getMe() {
        return "c";
	}
}