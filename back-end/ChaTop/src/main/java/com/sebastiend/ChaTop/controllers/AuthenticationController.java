package com.sebastiend.ChaTop.controllers;


import java.util.Map;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sebastiend.ChaTop.models.dto.UserDTO;
import com.sebastiend.ChaTop.models.dto.UserLoginDTO;
import com.sebastiend.ChaTop.models.dto.UserRegisterDTO;
import com.sebastiend.ChaTop.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Authentication", description = "All routes for the authentication.")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @Operation(summary = "Create a new user account", description = "Create a new user account.", tags = { "Authentication" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"}")
        )),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{}")
        ))
    })
    @PostMapping("/api/auth/register")
    public Map<String, String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        return authenticationService.saveUser(userRegisterDTO);
    }

    
    @Operation(summary = "Log in the user", description = "Log in the user.", tags = { "Authentication" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5ODc2NTQzMjEwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.3CGuVLhiJLvZfSW_2oB0QMkJNGtGRP6HF0ajCXtZmfI\"}")
        )),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\": \"error\"}")
        )),
    })
    @PostMapping("/api/auth/login")
    public Map<String, String> loginUser(@RequestBody UserLoginDTO userLogin) throws AuthenticationException {
        return authenticationService.loginUser(userLogin);
	}

    @Operation(summary = "Get the info of the current logged user", description = "Get the info of the current logged user.", tags = { "Authentication" })
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = 
            "{\n"+
                "  \"id\": 1,\n"+
                "  \"name\": \"test TEST\",\n"+
                "  \"email\": \"test@testa.com\",\n"+
                "  \"createdAt\": \"2025-01-13 20:36:38\",\n"+
                "  \"updatedAt\": \"2025-01-13 20:36:38\"\n"+
            "}"
            )
        )),
		@ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        )),
	})
    @GetMapping("/api/auth/me")
	public UserDTO getMe() {
        return authenticationService.getMe();
	}
}