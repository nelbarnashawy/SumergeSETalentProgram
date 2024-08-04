package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp3")
public class CourseImplementation3 implements CourseRecommender {

    @Override
    public String recommendCourse() {
        return "Physics is Recommended";
    }
}
