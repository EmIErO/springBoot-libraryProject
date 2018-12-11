package com.codecool.library;

import com.codecool.library.model.Book;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import com.codecool.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);
        System.out.println("WYSWIETL COS");
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
                System.out.println("Title: " + book.getTitle() + "  |  Author: " + book.getAuthor());
            }
            System.out.println("----------------------------------");

          



        };
    }


}
