package com.sebastiend.ChaTop.services;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.dto.Message.MessageDTO;
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

    public Map<String, String> saveMessage(MessageDTO messageDTO) throws IOException {
        if(messageDTO.getMessage() == null || messageDTO.getRental() == null) {
            return Map.of("message", "Some fields are empty.");
        }
        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(messageDTO.getMessage());
        /*String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(jwt);*/
        UserEntity user = userRepository.findById(messageDTO.getUser()).orElse(null);
        newMessage.setUser(user);
        RentalEntity rental = rentaRepository.findById(messageDTO.getRental()).orElse(null);
        newMessage.setRental(rental);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newMessage.setCreatedAt(dateFormatter.format(currentDate));
        newMessage.setUpdatedAt(dateFormatter.format(currentDate));
        messageRepository.save(newMessage);
        return Map.of("message", "Message send with success");
    }
}
