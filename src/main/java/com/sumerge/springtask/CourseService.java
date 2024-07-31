package com.sumerge.springtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRecommender courseRecommender;

    @Autowired
    public CourseService(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;

    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
    }
}
