package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AuthorDTO {

    @NotNull
    private String author_name;
    @Email
    @NotNull
    private String email;
    private Date author_birthdate;

    public AuthorDTO(String author_name, String email, Date author_birthdate) {
        this.author_name = author_name;
        this.email = email;
        this.author_birthdate = author_birthdate;
    }

    public @NotNull String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(@NotNull String author_name) {
        this.author_name = author_name;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public Date getAuthor_birthdate() {
        return author_birthdate;
    }

    public void setAuthor_birthdate(Date author_birthdate) {
        this.author_birthdate = author_birthdate;
    }
}
