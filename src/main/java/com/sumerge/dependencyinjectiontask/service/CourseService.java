package com.sumerge.dependencyinjectiontask.service;

import com.sumerge.dependencyinjectiontask.CourseRecommender;

public class CourseService {
    private final CourseRecommender courseRecommender;

    public CourseService(CourseRecommender courseRecommender)  {
        this.courseRecommender = courseRecommender;
    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
    }
}
