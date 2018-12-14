package com.codecool.library.service;

import com.codecool.library.model.User;
import com.codecool.library.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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

    public User findById(Long id) {
        return userRepo.findUserById(id);
    }

    public void addUser(User newUser) {
        userRepo.save(newUser);
    }

    public void changeUsersData(Long id, User user) {
        User userToUpdate = userRepo.findUserById(id);
        if (stringDataIsValid(user.getFirstName())) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (stringDataIsValid(user.getLastName())) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (stringDataIsValid(user.getEmail())) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (stringDataIsValid(user.getPesel())) {
            userToUpdate.setPesel(user.getPesel());
        }
        userRepo.save(userToUpdate);
    }

    private boolean stringDataIsValid(String data) {
        data = data.trim();
        if (data.equals(null) || data.isEmpty()) {
            return false;
        }
        return true;
    }

    public void removeUserById(Long id) {
        User user = findById(id);
        if (user != null) {
            user.setDeleted(1);
            userRepo.save(user);
        }
    }
}
