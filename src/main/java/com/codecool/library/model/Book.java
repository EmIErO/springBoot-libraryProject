package com.codecool.library.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

//@Component
@Entity
@Where(clause = "DELETED = 0")
@Table(name = "BOOK")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String author;
    @Column
    private String title;
    @Column(name="DELETED")
    private int deleted;

    @OneToMany (mappedBy="book")
    private Set<Specimen> specimens;



    public Book() {}

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        this.deleted = 0;
    }

    public Long getId() {
        return id;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = 1;
    }

}
