package com.codecool.library.controller;

import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SpecimenController {

    static final Logger LOGGER = LogManager.getLogger("SpecimenController:");

    @Autowired
    private SpecimenRepository specimenRepo;
    @Autowired
    private BorrowingRepository borrowingRepo;


    @GetMapping("/library/specimen")
    public List<Specimen> getAllSpecimen () {
        getLogger("Method: get -> getAllSpecimen");
        return specimenRepo.findAll();
    }


    @PutMapping("/library/specimen/{id}")
    public Specimen updateSpecimen(@RequestBody Specimen newSpecimen, @PathVariable Long id) {
        Specimen specimenToUpdate = specimenRepo.findById(id).get();

        updateBySetters(specimenToUpdate, newSpecimen);

        return specimenRepo.save(specimenToUpdate);
    }

    private void updateBySetters(Specimen specimenToUpdate, Specimen newSpecimen) {

        specimenToUpdate.setBookingTime(newSpecimen.getBookingTime());
        specimenToUpdate.setPublishment(newSpecimen.getPublishment());
        getLogger("Method: put -> updateSpecimen -> updateBySetters -> set specimen");
    }


    @PostMapping("/library/specimen")
    public Specimen addNewSpecimen(@RequestBody Specimen newSpecimen) {

        specimenRepo.save(newSpecimen);
        getLogger("Method: post -> addNewSpecimen -> save specimen");

        return newSpecimen;
    }


    @DeleteMapping("/library/specimen/{id}")
    public void deleteSpecimen(@PathVariable Long id) {
        Specimen specimenToDelete = specimenRepo.findById(id).get();

//        delete(specimenToDelete, id);
        archive(specimenToDelete, id);

    }


    private void delete(Specimen specimenToDelete, Long id){

        Set<Borrowing> borrowingToDeleteSet = specimenToDelete.getBorrowing();

        for (Borrowing borrow : borrowingToDeleteSet) {
            Long idBorrowingToDelete = borrow.getId();
            borrowingRepo.deleteById(idBorrowingToDelete);
            getLogger("Method: delete -> deleteSpecimenById -> delete borrowingSecimen");
        }

        specimenRepo.deleteById(id);
        getLogger("Method: delete -> deleteSpecimenById -> delete specimen");
    }

    private void archive(Specimen specimenToArchive, Long id) {
        //Long id = specimenToArchive.getId();
        Set<Borrowing> borrowingToArchiveSet = specimenToArchive.getBorrowing();

        for (Borrowing borrow : borrowingToArchiveSet) {
            borrow.setDeleted();
            borrowingRepo.save(borrow);
            getLogger("Method: delete -> deleteSpecimenById -> archive borrowingSecimen");
        }

        specimenToArchive.setDeleted();
        specimenRepo.save(specimenToArchive);
        getLogger("Method: delete -> deleteSpecimenById -> archive specimen");
    }

    private void getLogger(String info) {
        LOGGER.info(info);
    }
}
