package com.codecool.library.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Table(name = "BORROWING")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Specimen specimen;

    @ManyToOne
    @JoinColumn
    private User user;

    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Temporal(TemporalType.DATE)
    private Date expire;



}
