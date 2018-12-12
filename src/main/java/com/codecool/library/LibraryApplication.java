package com.codecool.library;

import com.codecool.library.model.Book;
import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import com.codecool.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);
        System.out.println("************************************");
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepo,
                                  BookRepository bookRepo,
                                  SpecimenRepository specimenRepo,
                                  BorrowingRepository borrowingRepo ) {
        return(args) -> {
            System.out.println("----------------------------------");
            List<Book> bookList = bookRepo.findAllByOrderByTitle();

            for (Book book : bookList) {
                System.out.println("Title: " + book.getTitle() + "   |  Author: " + book.getAuthor());
            }
            System.out.println("----------------------------------");


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
           // System.out.println(dateFormat.format(today));

            List<Borrowing> borrowingList = borrowingRepo.getBorrowingByReturnDateIsNullAndExpireBefore(today);
            for (Borrowing borrow : borrowingList) {
                System.out.println(borrow.getUser().getEmail() + " |  " + borrow.getSpecimen().getBook().getTitle());
            }

            System.out.println("----------------------------------");

            List<Specimen> specimenList = specimenRepo.findAllByBook_Title("How to be a Hero to Your Kids");
            for (Specimen spec : specimenList) {
                System.out.println(spec.getBook().getTitle());
            }

            System.out.println("----------------------------------");

            List<Book> availableList = bookRepo.findMyQuery();
            for (Book ava : availableList) {
                System.out.println(ava.getTitle());
            }



        };
    }


}
