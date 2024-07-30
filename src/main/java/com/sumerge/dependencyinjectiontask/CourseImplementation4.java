package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp4")
public class CourseImplementation4 implements CourseRecommender2{

    @Override
    public String recommendCourse() {
        return "Chemistry is Recommended";
    }
}
