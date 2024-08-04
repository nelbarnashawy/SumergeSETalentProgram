package com.sumerge.dependencyinjectiontask;

import org.example.CourseImplementation2;

public class OverridenCourseImplementation2 extends CourseImplementation2 {

    @Override
    public String recommendCourse() {
        return "CS 2 is Recommended";
    }
}
