package com.sumerge.dependencyinjectiontask.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Course {
    private int courseID;
    private String courseName;
    private String courseDescription;
    private int courseCredit;

    @Autowired
    public Course(String courseName, String courseDescription, int courseCredit) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseCredit = courseCredit;
    }

    public Course() {

    }

    private int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

}
