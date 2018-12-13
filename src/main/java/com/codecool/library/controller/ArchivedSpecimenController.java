package com.codecool.library.controller;

import com.codecool.library.model.Specimen;
import com.codecool.library.repository.ArchivedSpecimenRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ArchivedSpecimenController{
    private ArchivedSpecimenRepository archivedSpecimenRepo;


    public ArchivedSpecimenController(ArchivedSpecimenRepository archivedSpecimenRepo) {
        this.archivedSpecimenRepo = archivedSpecimenRepo;
    }

    @GetMapping("/library/specimen/archived")
    public List<Specimen> getAllDeletedSpecimen () {
        return archivedSpecimenRepo.findAllArchived();
    }



}
