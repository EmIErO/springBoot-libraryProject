package com.codecool.library.controller;

import com.codecool.library.model.User;
import com.codecool.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/library/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/library/users")
    public void addUser(@RequestBody User u) {
        User newUser = new User(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPesel());
        userService.addUser(newUser);
    }

    @PutMapping("/library/users/{id}")
    public void changeUsersData(@PathVariable Long id, @RequestBody User user) {
        userService.changeUsersData(id, user);
    }

    @GetMapping("/library/users/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
