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
    private String author_email;
    private Date author_birthdate;

    public Author(Long id, String author_name, String author_email, Date author_birthdate) {
        this.id = id;
        this.author_name = author_name;
        this.author_email = author_email;
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

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public Date getAuthor_birthdate() {
        return author_birthdate;
    }

    public void setAuthor_birthdate(Date author_birthdate) {
        this.author_birthdate = author_birthdate;
    }
}
