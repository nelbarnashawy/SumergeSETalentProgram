package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp5")
public class CourseImplementation5 implements CourseRecommender3 {

    @Override
    public String recommendCourse() {
        return "English is Recommended";
    }
}
