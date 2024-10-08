package com.sumerge.springtask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    @NotNull(message = "Name is required")
    private String authorName;
    @Email
    @NotNull(message = "Email is required")
    private String email;
    private String authorBirthdate;

}
