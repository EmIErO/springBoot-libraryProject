package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class BookController {
    static final Logger LOGGER = LogManager.getLogger("BookController:");

    private BookRepository bookRepo;
    private SpecimenRepository specimenRepo;

    public BookController(BookRepository bookRepo, SpecimenRepository specimenRepo) {
        this.bookRepo = bookRepo;
        this.specimenRepo = specimenRepo;
    }

    @GetMapping("/library/book")
    public List<Book> getAllBook() {
        getLogger("Method: get -> getAllBook");
        return bookRepo.findAllByOrderByTitle();
    }

    @GetMapping("/library/book/rental")
    public List<Book> getAvailableBooks() {
        getLogger("Method: get -> getAvailableBook");
        return bookRepo.findAllAvailableBooks();
    }

    @GetMapping("/library/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        getLogger("Method: get -> getBookById");
        return bookRepo.findById(id).get();
    }

    @PostMapping("/library/book")
    public Book addNewBook(@RequestBody Book newBook) {

        bookRepo.save(newBook);

        Set<Specimen> specimenSet = newBook.getSpecimens();
        for (Specimen spec : specimenSet) {
            spec.setBook(newBook);
            specimenRepo.save(spec);
            getLogger("Method: post -> addNewSpecimen");
        }
        getLogger("Method: post -> addNewBook");
        return newBook;
    }


    @DeleteMapping("/library/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book bookToDelete = bookRepo.findById(id).get();

        Set<Specimen> specimenSet = bookToDelete.getSpecimens();
        for (Specimen spec : specimenSet) {
            Long idToDelete = spec.getId();
            specimenRepo.deleteById(idToDelete);
            LOGGER.info("specimen was deleted");
        }
        bookRepo.deleteById(id);
        LOGGER.info("book was deleted");
    }

    @PutMapping("/library/book/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book bookToUpdate = bookRepo.findById(id).get();

        bookToUpdate.setAuthor(newBook.getAuthor());
        bookToUpdate.setTitle(newBook.getTitle());
        LOGGER.info("book was updated");

//        Set<Specimen> newSpecimenSet = newBook.getSpecimens();
//
//        for(Specimen spec : newSpecimenSet) {
//            Long specimenId = spec.getId();
//            Specimen specToUpdate = specimenRepo.findById(31L).get();
//            specToUpdate.setBookingTime(spec.getBookingTime());
//            specToUpdate.setPublishment(spec.getPublishment());
//            specimenRepo.save(specToUpdate);
//        }

        return bookRepo.save(bookToUpdate);
    }

    private void getLogger(String info) {
        LOGGER.info(info);
    }

}
