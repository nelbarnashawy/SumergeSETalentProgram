package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component
public class CourseImplementation2 implements CourseRecommender{

    public CourseImplementation2() {}

    @Override
    public String recommendCourse() {
        return "CS is Recommended";
    }
}
