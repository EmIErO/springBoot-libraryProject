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
    @JoinColumn (name = "specimen_id")
    private Specimen specimen;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date expire;

    public Borrowing(){}

    public Borrowing(Specimen specimen, User user, Date expire) {
        this.specimen = specimen;
        this.user = user;
        this.returnDate = null;
        this.expire = expire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}
