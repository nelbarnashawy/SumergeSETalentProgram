package com.sumerge.springtask.model;

import jakarta.persistence.*;


@Entity
@Table(name ="Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String course_name;
    private String course_description;
    private int course_credit;

    public Course(Long id, String course_name, String course_description, int course_credit) {
        this.id = id;
        this.course_name = course_name;
        this.course_description = course_description;
        this.course_credit = course_credit;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(int course_credit) {
        this.course_credit = course_credit;
    }
}
