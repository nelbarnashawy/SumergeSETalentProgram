package com.sumerge.dependencyinjectiontask.configuration;

import com.sumerge.dependencyinjectiontask.CourseImplementation1;
import com.sumerge.dependencyinjectiontask.CourseImplementation2;
import com.sumerge.dependencyinjectiontask.CourseImplementation3;
import com.sumerge.dependencyinjectiontask.CourseRecommender;
import com.sumerge.dependencyinjectiontask.service.CourseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseRecommenderConfig {

    @Bean
    public CourseService courseService(@Qualifier("courseImplementation3") CourseRecommender courseRecommender) {
        return new CourseService(courseRecommender);
    }
    @Bean
    public CourseRecommender courseImplementation1() {
        return new CourseImplementation1();
    }

    @Bean
    public CourseRecommender courseImplementation2() {
        return new CourseImplementation2();
    }

    @Bean
    public CourseRecommender courseImplementation3() {
        return new CourseImplementation3();
    }

}
