package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotNull(message = "Name is required")
    private String courseName;
    private String courseDescription;
    private int courseCredit;

}
