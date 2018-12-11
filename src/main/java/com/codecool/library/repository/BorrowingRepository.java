package com.codecool.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.Borrowing;

import java.util.Date;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> getBorrowingByReturnDateEqualsAndExpireBefore(Date returnDate, Date expire);
}
