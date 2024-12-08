package com.sebastiend.ChaTop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> getUser(final Long id) {
        // Renvoyer un objet reformaté avec données utiles uniquement
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity saveUser(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);
        return savedUser;
    }
}
