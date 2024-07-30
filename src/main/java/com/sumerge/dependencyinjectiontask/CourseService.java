package com.sumerge.dependencyinjectiontask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRecommender courseRecommender;
    private final CourseRecommender2 courseRecommender2;

    @Autowired
    public CourseService(CourseRecommender courseRecommender, @Qualifier("courseImp3") CourseRecommender2 courseRecommender2) {
        this.courseRecommender = courseRecommender;
        this.courseRecommender2 = courseRecommender2;
    }
}
