package com.sumerge.dependencyinjectiontask.service;

import com.sumerge.dependencyinjectiontask.CourseRecommender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRecommender courseRecommender;

    public CourseService(@Qualifier("courseImplementation3") CourseRecommender courseRecommender)  {
        this.courseRecommender = courseRecommender;
    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
    }
}
