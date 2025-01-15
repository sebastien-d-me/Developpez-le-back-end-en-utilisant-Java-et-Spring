package com.sebastiend.ChaTop.controllers;


import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sebastiend.ChaTop.models.dto.MessageDTO;
import com.sebastiend.ChaTop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Messages", description = "All routes for the messages.")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Create a message", description = "Create a message.", tags = { "Messages" })
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"message\": \"Message send with success\"}")
        )),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "")
        )),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{}")
        ))
	})
    @PostMapping("/api/messages")
    public Map<String, String> createRental(@RequestBody MessageDTO message) throws IOException {
        return messageService.saveMessage(message);
    }
}
