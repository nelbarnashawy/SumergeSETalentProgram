package com.sumerge.dependencyinjectiontask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRecommender courseRecommender;
    private final CourseRecommender2 courseRecommender2;
    private final CourseRecommender3 courseImp6;

    @Autowired
    public CourseService(CourseRecommender courseRecommender, @Qualifier("courseImp3") CourseRecommender2 courseRecommender2, CourseRecommender3 courseImp6) {
        this.courseRecommender = courseRecommender;
        this.courseRecommender2 = courseRecommender2;
        this.courseImp6=courseImp6;
    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
        System.out.println(courseRecommender2.recommendCourse());
        System.out.println(courseImp6.recommendCourse());
    }
}
