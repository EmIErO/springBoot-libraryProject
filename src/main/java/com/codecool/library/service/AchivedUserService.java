package com.codecool.library.service;

import com.codecool.library.model.User;
import com.codecool.library.repository.ArchivedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchivedUserService {

    @Autowired
    private ArchivedUserRepository archUserRepo;

    public List<User> findArchivedUsers() {
        return archUserRepo.findAllArchivedUsers();
    }
}
