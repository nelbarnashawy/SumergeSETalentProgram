package com.sumerge.dependencyinjectiontask;

public class CourseImplementation2 implements CourseRecommender{

    @Override
    public String recommendCourse() {
        return "CS is Recommended";
    }
}
