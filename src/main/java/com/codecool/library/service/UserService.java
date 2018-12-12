package com.codecool.library.service;

import com.codecool.library.model.User;
import com.codecool.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public List<User> findById(Long id) {
        return userRepo.findUserById(id);
    }
}
