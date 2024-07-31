package com.sumerge.springtask;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("courseImp")
public class CourseImplementation implements CourseRecommender{

    @Override
    public String recommendCourse() {
        return "Math is Recommended";
    }
}
