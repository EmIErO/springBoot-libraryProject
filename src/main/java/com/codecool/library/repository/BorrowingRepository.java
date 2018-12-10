package com.codecool.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
