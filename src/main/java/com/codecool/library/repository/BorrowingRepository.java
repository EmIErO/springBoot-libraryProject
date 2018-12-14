package com.codecool.library.repository;

import com.codecool.library.model.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.Borrowing;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> getBorrowingByReturnDateIsNullAndExpireBefore(Date date);

    List<Borrowing> findAll();
    List<Borrowing> findAllBySpecimenId(Long id);
    List<Borrowing> findAllByUserId(Long id);



}
