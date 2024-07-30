package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp1")
public class CourseImplementation1 implements CourseRecommender{

    @Override
    public String recommendCourse() {
        return "Math is Recommended";
    }
}
