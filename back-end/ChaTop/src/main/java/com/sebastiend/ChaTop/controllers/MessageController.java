package com.sebastiend.ChaTop.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.services.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Messages", description = "All routes for the messages.")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Create a message", description = "Create a message.", tags = { "Messages" })
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "400", description = "Bad Request")
	})
    @PostMapping("/api/messages")
    public Map<String, String> createRental(@ModelAttribute MessageEntity message, @RequestParam("user_id") Integer user, @RequestParam("rental_id") Integer rental) throws IOException {
        return messageService.saveMessage(message, user, rental);
    }
}
