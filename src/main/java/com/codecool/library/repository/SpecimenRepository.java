package com.codecool.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.*;
import java.util.List;

public interface SpecimenRepository extends JpaRepository<Specimen, Long> {
    List<Specimen> findAllByBook_Title(String title);

//    List<Specimen> findAllByBook_TitleAndBorrowingReturnDateIsNull(String title);
}
