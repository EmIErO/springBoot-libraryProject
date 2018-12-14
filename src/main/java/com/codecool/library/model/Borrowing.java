package com.codecool.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.Date;

//@Component
@Entity
@Where(clause = "DELETED = 0")
@Table(name = "BORROWING")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "specimen_id")
    private Specimen specimen;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date expire;

    @Column(name="DELETED")
    private int deleted;

    public Borrowing(){}

    public Borrowing(Specimen specimen, User user, Date expire) {
        this.specimen = specimen;
        this.user = user;
        this.returnDate = null;
        this.expire = expire;
        this.deleted = 0;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = 1;
    }
}
