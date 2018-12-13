package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SpecimenController {

    static final Logger LOGGER = LogManager.getLogger("SpecimenController:");

    private SpecimenRepository specimenRepo;
    private BorrowingRepository borrowingRepo;


    public SpecimenController(SpecimenRepository specimenRepo, BorrowingRepository borrowingRepo) {
        this.specimenRepo = specimenRepo;
        this.borrowingRepo = borrowingRepo;
    }

    @GetMapping("/library/specimen")
    public List<Specimen> getAllSpecimen () {
        LOGGER.info("get all specimen" );
        return specimenRepo.findAll();
    }


    @PutMapping("/library/specimen/{id}")
    public Specimen updateSpecimen(@RequestBody Specimen newSpecimen, @PathVariable Long id) {
        Specimen specimenToUpdate = specimenRepo.findById(id).get();

        specimenToUpdate.setBookingTime(newSpecimen.getBookingTime());
        specimenToUpdate.setPublishment(newSpecimen.getPublishment());

        return specimenRepo.save(specimenToUpdate);
    }

    @PostMapping("/library/specimen")
    public Specimen addNewSpecimen(@RequestBody Specimen newSpecimen) {

        specimenRepo.save(newSpecimen);
        LOGGER.info("new specimen was added");

        return newSpecimen;
    }


    @DeleteMapping("/library/specimen/{id}")
    public void deleteSpecimen(@PathVariable Long id) {
        Specimen specimenToDelete = specimenRepo.findById(id).get();

//        delete(specimenToDelete, id);
        archive(specimenToDelete, id);


//        Set<Borrowing> borrowingToDeleteSet = specimenToDelete.getBorrowing();
//
//        for (Borrowing borrow : borrowingToDeleteSet) {
//            Long idToDelete = borrow.getId();
////            borrowingRepo.deleteById(idToDelete);
//            borrow.setDeleted();
//            borrowingRepo.save(borrow);
//        }
//
////        specimenRepo.deleteById(id);
//        specimenToDelete.setDeleted();
//
//        specimenRepo.save(specimenToDelete);
    }


    private void delete(Specimen specimenToDelete, Long id){
        //Long id = specimenToDelete.getId();
        Set<Borrowing> borrowingToDeleteSet = specimenToDelete.getBorrowing();

        for (Borrowing borrow : borrowingToDeleteSet) {
            Long idBorrowingToDelete = borrow.getId();
            borrowingRepo.deleteById(idBorrowingToDelete);
        }

        specimenRepo.deleteById(id);
    }

    private void archive(Specimen specimenToArchive, Long id) {
        //Long id = specimenToArchive.getId();
        Set<Borrowing> borrowingToArchiveSet = specimenToArchive.getBorrowing();

        for (Borrowing borrow : borrowingToArchiveSet) {
            borrow.setDeleted();
            borrowingRepo.save(borrow);
        }

        specimenToArchive.setDeleted();
        specimenRepo.save(specimenToArchive);
    }


}
