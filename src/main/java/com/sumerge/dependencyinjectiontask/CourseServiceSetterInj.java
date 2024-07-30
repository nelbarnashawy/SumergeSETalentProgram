package com.sumerge.dependencyinjectiontask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceSetterInj {
    private CourseRecommender courseRecommender;

    @Autowired
    public void setCourseRecommender(CourseRecommender courseRecommender){
        this.courseRecommender=courseRecommender;
    }
}
