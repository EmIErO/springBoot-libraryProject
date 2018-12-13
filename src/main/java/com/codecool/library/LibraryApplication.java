package com.codecool.library;

import com.codecool.library.model.Book;
import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.model.User;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import com.codecool.library.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class LibraryApplication {

    private static final Logger logger = LogManager.getFormatterLogger(LibraryApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);
        logger.info("it is me");
    }









    @Bean
    public CommandLineRunner demo(UserRepository userRepo,
                                  BookRepository bookRepo,
                                  SpecimenRepository specimenRepo,
                                  BorrowingRepository borrowingRepo) {
        return(args) -> {
            System.out.println("----------------------------------");
            List<Book> bookList = bookRepo.findAllByOrderByTitle();

            for (Book book : bookList) {
                logger.info("Title: " + book.getTitle() + "   |  Author: " + book.getAuthor());
            }
            System.out.println("----------------------------------");


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
           // System.out.println(dateFormat.format(today));

            List<Borrowing> borrowingList = borrowingRepo.getBorrowingByReturnDateIsNullAndExpireBefore(today);
            for (Borrowing borrow : borrowingList) {
                logger.info(borrow.getUser().getEmail() + " |  " + borrow.getSpecimen().getBook().getTitle());
            }

            System.out.println("----------------------------------");

            Set<Specimen> specimenSet = specimenRepo.findAllByBook_Title("How to be a Hero to Your Kids");
            for (Specimen spec : specimenSet) {
                logger.info(spec.getBook().getTitle());
            }

            System.out.println("----------------------------------");

            List<Book> availableList = bookRepo.findAllByOrderByTitle();
            for (Book ava : availableList) {
                System.out.println(ava.getTitle());
            }
            List<User> users = userRepo.findAll();

            for (User u: users) {
                System.out.println(u.getFirstName() + "   " + u.getLastName());
            }



        };
    }


}
