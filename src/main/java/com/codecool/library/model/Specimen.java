package com.codecool.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SPECIMEN")
public class Specimen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String publisment;
    @Column
    private int bookingTime;

    @ManyToOne
    private Book book;

    @OneToMany(mappedBy = "specimen")
    Set<Borrowing> borrowing;


    public Specimen() {

    }

    public Specimen(String publisment, int bookingTime) {
        this.publisment = publisment;
        this.bookingTime = bookingTime;
    }


    public String getPublisment() {
        return publisment;
    }

    public void setPublisment(String publisment) {
        this.publisment = publisment;
    }

    public int getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(int bookingTime) {
        this.bookingTime = bookingTime;
    }
}
