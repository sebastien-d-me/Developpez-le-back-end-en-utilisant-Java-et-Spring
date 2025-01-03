package com.sebastiend.ChaTop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public Map<String, String> saveUser(UserEntity user) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(password);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now();
        newUser.setCreatedAt(dateFormatter.format(currentDate));
        newUser.setUpdatedAt(dateFormatter.format(currentDate));
        userRepository.save(newUser);
        return Map.of("token", "TOKEN JWT");
    }
}
