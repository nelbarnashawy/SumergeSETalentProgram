package com.sumerge.springtask.model;

import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author_name;
    private String email;
    private Date author_birthdate;

    public Author(Long id, String author_name, String email, Date author_birthdate) {
        this.id = id;
        this.author_name = author_name;
        this.email = email;
        this.author_birthdate = author_birthdate;
    }

    public Author() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAuthor_birthdate() {
        return author_birthdate;
    }

    public void setAuthor_birthdate(Date author_birthdate) {
        this.author_birthdate = author_birthdate;
    }
}
