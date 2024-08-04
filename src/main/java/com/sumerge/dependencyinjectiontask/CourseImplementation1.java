package com.sumerge.dependencyinjectiontask;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("courseImp1")
public class CourseImplementation1 implements CourseRecommender{

    public CourseImplementation1() {}

    @Override
    public String recommendCourse() {
        return "Math is Recommended";
    }
}