package com.sebastiend.ChaTop.controllers;


import com.sebastiend.ChaTop.models.dto.Message.MessageDTO;
import com.sebastiend.ChaTop.models.dto.Message.MessageResponseDTO;
import com.sebastiend.ChaTop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Messages", description = "All routes for the messages.")
public class MessageController {
    @Autowired
    private MessageService messageService;


    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{\"message\": \"Message send with success\"}"
            ),
            mediaType = "application/json"
        )),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = ""
            ),
            mediaType = "application/json" 
        )),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                value = "{}"
            ),
            mediaType = "application/json"
        ))
	})
    @Operation(summary = "Create a message", description = "Create a message.", tags = { "Messages" })
    @PostMapping("/api/messages")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO message) throws IOException {
        try {
            MessageResponseDTO messageResponseDTO = messageService.saveMessage(message);
            return ResponseEntity.ok().body(messageResponseDTO);
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body("{}");
        }
    }
}
