package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AuthorDTO {

    @NotNull
    private String author_name;
    @Email
    @NotNull
    private String author_email;
    private Date author_birthdate;

    public AuthorDTO(String author_name, String author_email, Date author_birthdate) {
        this.author_name = author_name;
        this.author_email = author_email;
        this.author_birthdate = author_birthdate;
    }

    public @NotNull String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(@NotNull String author_name) {
        this.author_name = author_name;
    }

    public @Email @NotNull String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(@Email @NotNull String author_email) {
        this.author_email = author_email;
    }

    public Date getAuthor_birthdate() {
        return author_birthdate;
    }

    public void setAuthor_birthdate(Date author_birthdate) {
        this.author_birthdate = author_birthdate;
    }
}
