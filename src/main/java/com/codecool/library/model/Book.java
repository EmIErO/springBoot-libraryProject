package com.codecool.library.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String author;
    @Column
    private String title;

    private Set<Specimen> specimens;

    public Book() {}

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Specimen> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(Set<Specimen> specimens) {
        this.specimens = specimens;
    }
}
