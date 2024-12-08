package com.sebastiend.ChaTop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastiend.ChaTop.models.entities.UserEntity;
import com.sebastiend.ChaTop.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<UserEntity> getUsers() {
        return userService.getUsers();
    }
}