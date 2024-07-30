package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp2")
public class CourseImplementation2 implements CourseRecommender{

    @Override
    public String recommendCourse() {
        return "CS is Recommended";
    }
}
