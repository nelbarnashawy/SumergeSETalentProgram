package com.sumerge.dependencyinjectiontask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRecommender courseRecommender;

    @Autowired
    public CourseService(@Qualifier("courseImp3") CourseRecommender courseRecommender)  {
        this.courseRecommender = courseRecommender;
    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
    }
}
