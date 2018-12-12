package com.codecool.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SPECIMEN")
public class Specimen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String publishment;
    @Column
    private int bookingTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;


    @OneToMany(mappedBy = "specimen")
    Set<Borrowing> borrowing;


    public Specimen() {

    }

    public Specimen(String publishment, int bookingTime, Book book) {
        this.publishment = publishment;
        this.bookingTime = bookingTime;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public String getPublishment() {
        return publishment;
    }

    public void setPublishment(String publishment) {
        this.publishment = publishment;
    }

    public int getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(int bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
