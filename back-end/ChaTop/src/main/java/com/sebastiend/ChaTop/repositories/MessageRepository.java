package com.sebastiend.ChaTop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebastiend.ChaTop.models.entities.MessageEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
    
}
