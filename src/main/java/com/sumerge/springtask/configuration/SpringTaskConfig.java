package com.sumerge.springtask.configuration;

import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringTaskConfig {

        @Bean
        public CommandLineRunner run(CourseService courseService) {
            return args -> {
                List<Course> courses = courseService.findAll();
                courses.forEach(System.out::println);
            };
        }
    }


