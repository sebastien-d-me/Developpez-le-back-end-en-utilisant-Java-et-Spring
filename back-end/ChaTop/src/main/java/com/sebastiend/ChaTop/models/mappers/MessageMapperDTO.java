package com.sebastiend.ChaTop.models.mappers;

import com.sebastiend.ChaTop.models.dto.MessageDTO;
import com.sebastiend.ChaTop.models.dto.UserLoginDTO;
import com.sebastiend.ChaTop.models.dto.UserRegisterDTO;
import com.sebastiend.ChaTop.models.entities.MessageEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;

public class MessageMapperDTO {
    public static MessageDTO convertDTO(MessageEntity message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRental(message.getRental().getId());
        messageDTO.setUser(message.getUser().getId());
        messageDTO.setMessage(message.getMessage());
        return messageDTO;
    }
}
