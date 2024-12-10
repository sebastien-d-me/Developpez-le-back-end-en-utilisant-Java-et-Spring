package com.sebastiend.ChaTop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.services.MessageService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public Iterable<MessageEntity> getMessages() {
        return messageService.getMessages();
    }
}
