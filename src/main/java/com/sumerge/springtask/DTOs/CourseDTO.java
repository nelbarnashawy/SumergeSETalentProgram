package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {

    @NotNull
    private String courseName;
    private String courseDescription;
    @NotNull
    private int courseCredit;

}
