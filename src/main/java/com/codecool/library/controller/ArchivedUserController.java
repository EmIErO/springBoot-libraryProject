package com.codecool.library.controller;

import com.codecool.library.model.User;
import com.codecool.library.service.AchivedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArchivedUserController {

    @Autowired
    private AchivedUserService archUserService;

    @GetMapping("/library/users/archived")
    public List<User> findArchivedUsers() {
        return archUserService.findArchivedUsers();
    }

}
