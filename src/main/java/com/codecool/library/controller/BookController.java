package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.SpecimenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class BookController {
    static final Logger LOGGER = LogManager.getLogger("BookController:");

    private final BookRepository bookRepo;
    private final SpecimenRepository specimenRepo;

    @Autowired
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
    public Book addNewBook(@RequestBody @Valid Book newBook) {

        bookRepo.save(newBook);
        setSpecimen(newBook);

        getLogger("Method: post -> addNewBook -> add book");
        return newBook;
    }

    private void setSpecimen(Book newBook) {
        Set<Specimen> specimenSet = newBook.getSpecimens();
        for (Specimen specimen : specimenSet) {
            specimen.setBook(newBook);
            specimenRepo.save(specimen);
            getLogger("Method: post -> addNewBook -> set specimen");
        }
    }


    @DeleteMapping("/library/book/{id}")
    public void deleteBookById(@PathVariable Long id) {
        Book bookToDelete = bookRepo.findById(id).get();
        delete(bookToDelete, id);

    }


    private void delete(Book bookToDelete, Long id) {
        Set<Specimen> specimenSet = bookToDelete.getSpecimens();

        for (Specimen spec : specimenSet) {
            Long idToDelete = spec.getId();
            specimenRepo.deleteById(idToDelete);
            getLogger("Method: delete -> deleteBookById -> delete bookSpecimens");
        }

        bookRepo.deleteById(id);
        getLogger("Method: delete -> deleteBookById -> delete book");
    }

    private void archive(Book bookToArchive, Long id) {
        Set<Specimen> specimenSet = bookToArchive.getSpecimens();

        for (Specimen specimen : specimenSet) {
            specimen.setDeleted();
            specimenRepo.save(specimen);
            getLogger("Method: delete -> deleteBookById -> archive bookSpecimens");
        }

        bookToArchive.setDeleted();
        bookRepo.save(bookToArchive);
        getLogger("Method: delete -> deleteBookById -> archive book");
    }


    @PutMapping("/library/book/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book bookToUpdate = bookRepo.findById(id).get();

        bookToUpdate.setAuthor(newBook.getAuthor());
        bookToUpdate.setTitle(newBook.getTitle());
        getLogger("Method: put -> updateBook -> set Author&Title");

        return bookRepo.save(bookToUpdate);
    }

    private void getLogger(String info) {
        LOGGER.info(info);
    }




}
