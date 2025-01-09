package com.sebastiend.ChaTop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.repositories.MessageRepository;
import com.sebastiend.ChaTop.repositories.RentalRepository;
import com.sebastiend.ChaTop.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentaRepository;

    public Map<String, String> saveMessage(MessageEntity message) throws IOException {
        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(message.getMessage());
        String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(jwt);
        newMessage.setUser(user);
        newMessage.setRental(message.getRental());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newMessage.setCreatedAt(dateFormatter.format(currentDate));
        newMessage.setUpdatedAt(dateFormatter.format(currentDate));
        messageRepository.save(newMessage);
        return Map.of("message", "Message send with success");
    }
}
