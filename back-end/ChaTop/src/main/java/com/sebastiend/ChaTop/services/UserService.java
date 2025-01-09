package com.sebastiend.ChaTop.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private JWTService jwtService;

	
	public UserService(JWTService jwtService) {
		this.jwtService = jwtService;
	}

    public Optional<UserEntity> getUser(final Integer id) {
        return userRepository.findById(id);
    }

    public Map<String, String> saveUser(UserEntity user) {
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

    public Map<String, String> loginUser(String email, String password) throws AuthenticationException {
        // verifier que pas vide
        UserEntity userCheck = userRepository.findByEmail(email);
        // faire en sorte que les paramètres soit en post et pas en get (affiché dans l'URL)
        if(userCheck == null) {
            return Map.of("message", "error");
        }

        String passCheck = userCheck.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password, passCheck)) {
            String token = jwtService.generateTokenLogin(userCheck);
            return Map.of("token", token);
        } else {
            return Map.of("message", "error");
        }
    }
}
