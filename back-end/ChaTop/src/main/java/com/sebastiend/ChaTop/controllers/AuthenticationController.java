package com.sebastiend.ChaTop.controllers;


import com.sebastiend.ChaTop.models.dto.Users.*;
import com.sebastiend.ChaTop.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Authentication", description = "All routes for the authentication.")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"}"
            ),
            mediaType = "application/json"
        )),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{}"
            ),
            mediaType = "application/json"
        ))
    })
    @Operation(summary = "Create a new user account", description = "Create a new user account.", tags = { "Authentication" })
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            UserTokenDTO userTokenDTO = authenticationService.saveUser(userRegisterDTO);
            return ResponseEntity.ok().body(userTokenDTO);
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body("{}");
        }
    }

    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5ODc2NTQzMjEwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.3CGuVLhiJLvZfSW_2oB0QMkJNGtGRP6HF0ajCXtZmfI\"}"
            ),
            mediaType = "application/json"
        )),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{\"message\": \"error\"}"
            ),
            mediaType = "application/json"
        ))
    })
    @Operation(summary = "Log in the user", description = "Log in the user.", tags = { "Authentication" })
    @PostMapping("/api/auth/login")
    public Map<String, String> loginUser(@RequestBody UserLoginDTO userLogin) throws AuthenticationException {
        return authenticationService.loginUser(userLogin);
	}

    
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
        ))
	})
    @GetMapping("/api/auth/me")
    @Operation(summary = "Get the info of the current logged user", description = "Get the info of the current logged user.", tags = { "Authentication" })
    @SecurityRequirement(name = "bearerAuth")
	public UserDTO getMe() {
        return authenticationService.getMe();
	}
}