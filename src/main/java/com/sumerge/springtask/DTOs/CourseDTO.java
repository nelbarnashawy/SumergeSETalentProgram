package com.sumerge.springtask.DTOs;

import jakarta.validation.constraints.NotNull;


public class CourseDTO {

    @NotNull
    private String course_name;
    private String course_description;
    @NotNull
    private int course_credit;


    public CourseDTO(String course_name, String course_description, int course_credit) {
        this.course_name = course_name;
        this.course_description = course_description;
        this.course_credit = course_credit;
    }

    public @NotNull String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(@NotNull String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    @NotNull
    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(@NotNull int course_credit) {
        this.course_credit = course_credit;
    }
}
