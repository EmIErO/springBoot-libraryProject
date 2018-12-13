package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SpecimenController {

    private SpecimenRepository specimenRepo;
    private BorrowingRepository borrowingRepo;

    public SpecimenController(SpecimenRepository specimenRepo, BorrowingRepository borrowingRepo) {
        this.specimenRepo = specimenRepo;
        this.borrowingRepo = borrowingRepo;
    }

    @GetMapping("/library/specimen")
    public List<Specimen> getAllSpecimen () {

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

        return newSpecimen;
    }


    @DeleteMapping("/library/specimen/{id}")
    public void deleteSpecimen(@PathVariable Long id) {
        Specimen specimenToDelete = specimenRepo.findById(id).get();

        Set<Borrowing> borrowingSet = specimenToDelete.getBorrowing();
        for (Borrowing borrow : borrowingSet) {
            Long idToDelete = borrow.getId();
            borrowingRepo.deleteById(idToDelete);
        }

        specimenRepo.deleteById(id);
    }


}
