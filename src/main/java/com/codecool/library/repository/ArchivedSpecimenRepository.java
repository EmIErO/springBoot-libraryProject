package com.codecool.library.repository;

import com.codecool.library.model.Book;
import com.codecool.library.model.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArchivedSpecimenRepository extends JpaRepository<Specimen, Long> {

    @Query(value = "SELECT * FROM Specimen WHERE deleted=1",
            nativeQuery = true)
    List<Specimen> findAllArchived();
}



