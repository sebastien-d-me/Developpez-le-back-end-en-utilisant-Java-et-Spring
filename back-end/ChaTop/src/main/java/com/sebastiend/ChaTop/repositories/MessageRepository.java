package com.sebastiend.ChaTop.repositories;


import com.sebastiend.ChaTop.models.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {}