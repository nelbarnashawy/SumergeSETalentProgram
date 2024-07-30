package com.sumerge.dependencyinjectiontask;

public class CourseImplementation1 implements CourseRecommender{

    @Override
    public String recommendCourse() {
        return "Math is Recommended";
    }
}
