package com.sebastiend.ChaTop.services;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.dto.Users.UserDTO;
import com.sebastiend.ChaTop.models.dto.Users.UserLoginDTO;
import com.sebastiend.ChaTop.models.dto.Users.UserRegisterDTO;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.repositories.UserRepository;
import lombok.Data;


@Data
@Service

public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    private JWTService jwtService;

	
	public AuthenticationService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    public Map<String, String> saveUser(UserRegisterDTO userRegisterDTO) {
        if(userRegisterDTO.getName() == null || userRegisterDTO.getEmail() == null || userRegisterDTO.getPassword() == null) {
            return Map.of("message", "Some fields are empty.");
        }
        UserEntity user = UserMapperDTO.convertRegisterEntity(userRegisterDTO);
        UserEntity userCheck = userRepository.findByEmail(user.getEmail());
        if(userCheck != null) {
            return Map.of("message", "A user already exist with this email.");
        }

        UserEntity newUser = new UserEntity();
        // verifier si pas de doublon de mail
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
        String token = jwtService.generateTokenRegister(newUser);
        return Map.of("token", token);
    }

    public Map<String, String> loginUser(UserLoginDTO userLogin) throws AuthenticationException {
        if(userLogin.getEmail() == null || userLogin.getPassword() == null) {
            return Map.of("message", "Some fields are empty.");
        }
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        // verifier que pas vide
        UserEntity userCheck = userRepository.findByEmail(email);
        // faire en sorte que les paramètres soit en post et pas en get (affiché dans l'URL)
        if(userCheck == null) {
            return Map.of("message", "error no exist");
        }

        String passCheck = userCheck.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password, passCheck)) {
            String token = jwtService.generateTokenLogin(userCheck);
            return Map.of("token", token);
        } else {
            return Map.of("message", "error passowrd");
        }
    }

    public UserDTO getMe() {
        String jwt = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(jwt);
        return UserMapperDTO.convertDTO(user);
    }
}
