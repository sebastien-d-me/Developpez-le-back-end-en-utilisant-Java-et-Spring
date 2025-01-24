package com.sebastiend.ChaTop.services;


import com.sebastiend.ChaTop.models.dto.Users.*;
import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.repositories.UserRepository;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Data
@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    private JWTService jwtService;

	
	public AuthenticationService(JWTService jwtService) {
		this.jwtService = jwtService;
	}


    public UserTokenDTO saveUser(UserRegisterDTO userRegisterDTO) {
        if(userRegisterDTO.getName() == null || userRegisterDTO.getEmail() == null || userRegisterDTO.getPassword() == null) {
            throw new IllegalArgumentException("Some fields are empty.");
        }

        UserEntity user = UserMapperDTO.convertRegisterEntity(userRegisterDTO);
        UserEntity userCheckExist = userRepository.findByEmail(user.getEmail());
        if(userCheckExist != null) {
           throw new IllegalArgumentException("A user already exist with this email.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(password);
        newUser.setCreatedAt(dateFormatter.format(currentDate));
        newUser.setUpdatedAt(dateFormatter.format(currentDate));
        userRepository.save(newUser);

        String token = jwtService.generateToken(newUser);
        return new UserTokenDTO(token);
    }


    public Map<String, String> loginUser(UserLoginDTO userLogin) throws AuthenticationException {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        
        if(email == null || password == null) {
            return Map.of("message", "Some fields are empty.");
        }

        UserEntity userCheckExist = userRepository.findByEmail(email);
        if(userCheckExist == null) {
            return Map.of("message", "error no exist");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordCheckSame = userCheckExist.getPassword();

        if(passwordEncoder.matches(password, passwordCheckSame)) {
            String token = jwtService.generateToken(userCheckExist);
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
