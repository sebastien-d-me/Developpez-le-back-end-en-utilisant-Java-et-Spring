package com.sebastiend.ChaTop.repositories;


import com.sebastiend.ChaTop.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public UserEntity findByEmail(String email);
}
