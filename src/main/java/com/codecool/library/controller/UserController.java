package com.codecool.library.controller;

import com.codecool.library.model.User;
import com.codecool.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/library/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
