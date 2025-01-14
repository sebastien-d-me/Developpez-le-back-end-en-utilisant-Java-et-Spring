package com.sebastiend.ChaTop.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.sebastiend.ChaTop.models.entities.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public UserEntity findByEmail(String email);
}
