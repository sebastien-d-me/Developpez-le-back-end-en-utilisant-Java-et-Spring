package com.sebastiend.ChaTop.models.mappers;

import com.sebastiend.ChaTop.models.dto.MessageDTO;
import com.sebastiend.ChaTop.models.entities.MessageEntity;

public class MessageMapperDTO {
    public static MessageDTO convertDTO(MessageEntity message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setRental(message.getRental());
        messageDTO.setUser(message.getUser());
        messageDTO.setCreatedAt(message.getCreatedAt());
        messageDTO.setUpdatedAt(message.getUpdatedAt());
        return messageDTO;
    }
}
