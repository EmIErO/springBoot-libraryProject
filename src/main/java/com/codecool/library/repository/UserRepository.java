package com.codecool.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<UserRepository> findAllUser();

}


