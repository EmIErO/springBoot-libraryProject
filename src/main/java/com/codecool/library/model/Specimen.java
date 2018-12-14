package com.codecool.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Where(clause = "DELETED = 0")
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

    @Column(name="DELETED")
    private int deleted;


//    @JsonIgnore
    @OneToMany(mappedBy = "specimen")
    Set<Borrowing> borrowing;


    public Specimen() {

    }

    public Specimen(String publishment, int bookingTime, Book book) {
        this.publishment = publishment;
        this.bookingTime = bookingTime;
        this.book = book;
        this.deleted = 0;
    }

    public Set<Borrowing> getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Set<Borrowing> borrowing) {
        this.borrowing = borrowing;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = 1;
    }
}
