package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class BookController {

    private BookRepository bookRepo;
    private SpecimenRepository specimenRepo;

    public BookController(BookRepository bookRepo, SpecimenRepository specimenRepo) {
        this.bookRepo = bookRepo;
        this.specimenRepo = specimenRepo;
    }

    @GetMapping("/library/book")
    public List<Book> getAllBook() {

        return bookRepo.findAllByOrderByTitle();
    }

    @PostMapping("/library/book")
    public Book addNewBook(@RequestBody Book newBook) {

        bookRepo.save(newBook);

        Set<Specimen> specimenSet = newBook.getSpecimens();
        for (Specimen spec : specimenSet) {
            spec.setBook(newBook);
            specimenRepo.save(spec);
        }

        return newBook;
    }

    @DeleteMapping("/library/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book bookToDelete = bookRepo.findById(id).get();

        Set<Specimen> specimenSet = bookToDelete.getSpecimens();
        for (Specimen spec : specimenSet) {
            Long idToDelete = spec.getId();
            specimenRepo.deleteById(idToDelete);
        }
        bookRepo.deleteById(id);
    }

    @PutMapping("/library/book/{id}")
    public Book updateBook


//    @GetMapping("/library/book/{id}")
//    public Book getBookById(@PathVariable("id") Long id) {
//        return bookRepo.findById(id).get();
//    }




}
