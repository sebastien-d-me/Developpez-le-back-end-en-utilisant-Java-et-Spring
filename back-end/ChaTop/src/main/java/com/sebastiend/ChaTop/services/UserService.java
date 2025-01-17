package com.sebastiend.ChaTop.services;


import com.sebastiend.ChaTop.models.dto.Users.UserDTO;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.repositories.UserRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Optional<UserDTO> getUser(final Integer id) {
        return userRepository.findById(id).map(UserMapperDTO::convertDTO);
    }

}
