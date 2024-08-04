package com.sumerge.dependencyinjectiontask;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("courseImp1")
public class CourseImplementation1 implements CourseRecommender{

    @Override
    public String recommendCourses() {

        return ;
    }
}
