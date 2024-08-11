package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {

    @NotNull(message = "Name is required")
    private String courseName;
    private String courseDescription;
    @NotNull(message = "Credit is required")
    private int courseCredit;

}
