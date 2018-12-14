package com.codecool.library.repository;

import com.codecool.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArchivedUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE deleted = 1",
            nativeQuery = true)
    List<User> findAllArchivedUsers();
}
