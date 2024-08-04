package com.sumerge.dependencyinjectiontask.configuration;

import com.sumerge.dependencyinjectiontask.CourseImplementation1;
import com.sumerge.dependencyinjectiontask.CourseImplementation3;
import com.sumerge.dependencyinjectiontask.CourseRecommender;
import com.sumerge.dependencyinjectiontask.OverridenCourseImplementation2;
import com.sumerge.dependencyinjectiontask.service.CourseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "org.example")
public class CourseRecommenderConfig {

    @Bean
    public CourseService courseService(@Qualifier("courseImplementation2") CourseRecommender courseRecommender) {
        return new CourseService(courseRecommender);
    }
    @Bean
    public CourseRecommender courseImplementation1() {
        return new CourseImplementation1();
    }

    @Bean
    public CourseRecommender courseImplementation3() {
        return new CourseImplementation3();
    }

    @Bean(name = "courseImplementation2")
    public CourseRecommender courseImplementation2(){
        return new OverridenCourseImplementation2();
    }

}
