package com.sebastiend.ChaTop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public Map<String, String> saveMessage(MessageEntity message, Integer user, Integer rental) throws IOException {
        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(message.getMessage());
        newMessage.setUserId(user);
        newMessage.setRentalId(rental);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newMessage.setCreatedAt(dateFormatter.format(currentDate));
        newMessage.setUpdatedAt(dateFormatter.format(currentDate));
        messageRepository.save(newMessage);
        return Map.of("message", "Message send with success");
    }
}
