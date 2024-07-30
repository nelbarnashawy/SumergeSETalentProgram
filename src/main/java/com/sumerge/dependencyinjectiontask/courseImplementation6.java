package com.sumerge.dependencyinjectiontask;

import org.springframework.stereotype.Component;

@Component("courseImp6")
public class courseImplementation6 implements CourseRecommender3{

    @Override
    public String recommendCourse() {
        return "Arabic is Recommended";
    }
}
