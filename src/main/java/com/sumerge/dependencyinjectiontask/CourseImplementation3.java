package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component
public class CourseImplementation3 implements CourseRecommender {

    public CourseImplementation3() {}

    @Override
    public String recommendCourse() {
        return "Physics is Recommended";
    }
}
