package com.codecool.library.service;

import com.codecool.library.model.Book;
import com.codecool.library.model.Borrowing;
import com.codecool.library.model.Specimen;
import com.codecool.library.model.User;
import com.codecool.library.repository.BookRepository;
import com.codecool.library.repository.BorrowingRepository;
import com.codecool.library.repository.SpecimenRepository;
import com.codecool.library.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LibraryInitializer {

    public LibraryInitializer(UserRepository userRepo,
                              BookRepository bookRepo,
                              SpecimenRepository specimenRepo,
                              BorrowingRepository borrowingRepo) {


        User ania = new User ("Anna", "Idzi", "anna@idzi.pl", "83081705624");
        User amelia = new User ("Amelia", "Kwiatkowska", "amelia@kwiatkowska.pl", "08300804847");
        User piotr = new User ("Piotr", "Nowak", "piotr@nowak.pl", "89071004532");
        User tymon = new User ("Tymon", "Lewandowski", "tymonLewandowski", "20271106883");
        User wojtek = new User ("Wojciech", "Zawada", "wojtek@zawada.pl", "10250583435");

        userRepo.save(ania);
        userRepo.save(amelia);
        userRepo.save(piotr);
        userRepo.save(tymon);
        userRepo.save(wojtek);

        Book book1 = new Book ("Stephen R. Covey", "The Seven Habits of Highly Effective People");
        Book book2 = new Book ("Leo Babauta", "The Simple Guide To Minimalist Life");
        Book book3 = new Book ("Andrzej Bubrowiecki", "Popraw swoją skuteczność");
        Book book4 = new Book ("Josh McDowell", "How to be a Hero to Your Kids");
        Book book5 = new Book ("Tom Rath","How Full is Your Bucket?");

        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);
        bookRepo.save(book4);
        bookRepo.save(book5);


        Specimen specimen1Book1 = new Specimen ("2007", 30, book1);
        Specimen specimen2Book1 = new Specimen ("1996", 30, book1);
        Specimen specimen3Book1 = new Specimen ("1996", 30, book1);

        Specimen specimen1Book2 = new Specimen ("2013", 60, book2);
        Specimen specimen2Book2 = new Specimen ("2013", 60, book2);

        Specimen specimen1Book3 = new Specimen ("2009", 60, book3);
        Specimen specimen2Book3 = new Specimen ("2009", 60, book3);
        Specimen specimen3Book3 = new Specimen ("2009", 60, book3);

        Specimen specimen1Book4 = new Specimen ("1993", 60, book4);
        Specimen specimen2Book4 = new Specimen ("1993", 60, book4);
        Specimen specimen3Book4 = new Specimen ("1993", 60, book4);

        Specimen specimen1Book5 = new Specimen ("2004", 30, book5);

        specimenRepo.save(specimen1Book1);
        specimenRepo.save(specimen1Book2);
        specimenRepo.save(specimen1Book3);
        specimenRepo.save(specimen1Book4);
        specimenRepo.save(specimen1Book5);

        specimenRepo.save(specimen2Book1);
        specimenRepo.save(specimen2Book2);
        specimenRepo.save(specimen2Book3);
        specimenRepo.save(specimen2Book4);

        specimenRepo.save(specimen3Book1);
        specimenRepo.save(specimen3Book3);
        specimenRepo.save(specimen3Book4);

        try{
            Borrowing borrow1 = new Borrowing (specimen1Book5, ania, new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-10"));
//            borrow1.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-05"));

            Borrowing borrow2 = new Borrowing (specimen1Book2, ania, new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-10"));
            borrow2.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-10"));

            Borrowing borrow3 = new Borrowing (specimen1Book2, piotr, new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));
            borrow3.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-09"));

            Borrowing borrow4 = new Borrowing (specimen1Book1, amelia, new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-09"));

            Borrowing borrow5 = new Borrowing (specimen1Book4, amelia, new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-20"));

            borrowingRepo.save(borrow1);
            borrowingRepo.save(borrow2);
            borrowingRepo.save(borrow3);
            borrowingRepo.save(borrow4);
            borrowingRepo.save(borrow5);

        } catch (ParseException exception) {
            System.out.println("Bad date format YYYY-MM-DD");
        }

    }

}
