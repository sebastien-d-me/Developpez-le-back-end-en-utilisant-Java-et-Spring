package com.sebastiend.ChaTop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.repositories.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public MessageEntity saveMessage(MessageEntity message) {
        MessageEntity savedMessage = messageRepository.save(message);
        return savedMessage;
    }
}
