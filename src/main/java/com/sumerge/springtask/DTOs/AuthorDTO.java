package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {

    @NotNull(message = "Name is required")
    private String authorName;
    @Email
    @NotNull(message = "Email is required")
    private String email;
    private Date authorBirthdate;

}
