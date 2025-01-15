package com.sebastiend.ChaTop.services;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sebastiend.ChaTop.models.dto.UserDTO;
import com.sebastiend.ChaTop.models.mappers.UserMapperDTO;
import com.sebastiend.ChaTop.repositories.UserRepository;
import lombok.Data;


@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Optional<UserDTO> getUser(final Integer id) {
        return userRepository.findById(id).map(UserMapperDTO::convertDTO);
    }

}
