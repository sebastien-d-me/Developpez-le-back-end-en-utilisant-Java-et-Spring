package com.sebastiend.ChaTop.services;


import com.sebastiend.ChaTop.models.dto.Message.MessageDTO;
import com.sebastiend.ChaTop.models.dto.Message.MessageResponseDTO;
import com.sebastiend.ChaTop.models.entities.*;
import com.sebastiend.ChaTop.repositories.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentaRepository;


    public MessageResponseDTO saveMessage(MessageDTO messageDTO) throws IOException {
        if(messageDTO.getMessage() == null || messageDTO.getRental() == null) {
            throw new IllegalArgumentException("Some fields are empty.");
        }

        UserEntity user = userRepository.findById(messageDTO.getUser()).orElse(null);
        RentalEntity rental = rentaRepository.findById(messageDTO.getRental()).orElse(null);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(messageDTO.getMessage());
        newMessage.setUser(user);
        newMessage.setRental(rental);
        newMessage.setCreatedAt(dateFormatter.format(currentDate));
        newMessage.setUpdatedAt(dateFormatter.format(currentDate));
        messageRepository.save(newMessage);
        return new MessageResponseDTO("Message send with success");
    }
}
