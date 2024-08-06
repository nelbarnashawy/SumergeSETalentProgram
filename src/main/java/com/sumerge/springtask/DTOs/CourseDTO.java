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
    private String course_name;
    private String course_description;
    @NotNull
    private int course_credit;

}
