package com.codecool.library.controller;

import com.codecool.library.model.Specimen;
import com.codecool.library.repository.ArchivedSpecimenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ArchivedSpecimenController{
    @Autowired
    private ArchivedSpecimenRepository archivedSpecimenRepo;


    @GetMapping("/library/specimen/archived")
    public List<Specimen> getAllDeletedSpecimen () {
        return archivedSpecimenRepo.findAllArchived();
    }



}
