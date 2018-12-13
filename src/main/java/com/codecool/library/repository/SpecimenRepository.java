package com.codecool.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecool.library.model.*;
import java.util.List;
import java.util.Set;

public interface SpecimenRepository extends JpaRepository<Specimen, Long> {
    Set<Specimen> findAllByBook_Title(String title);
    List<Specimen> findAll();

}
