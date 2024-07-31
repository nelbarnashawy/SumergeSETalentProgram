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
                //Creating a new course test
                Course courseTest = new Course();
                courseTest.setCourse_name("CS");
                courseTest.setCourse_description("CS404 Course");
                courseTest.setCourse_credit(8);

                courseService.save(courseTest);
                System.out.println("Course saved successfully");

                //Finding course by ID
                System.out.println(courseService.findById(3));

                //Updating a course
                Course courseToUpdate = courseService.findById(3);
                courseService.update(courseToUpdate);
                System.out.println("Course updated successfully");
                System.out.println(courseService.findById(3));

                //Deleting a course
                Course courseToDelete = courseService.findById(2);
                courseService.delete(courseToDelete);
                System.out.println("Course deleted successfully");
                if(courseService.findById(2) == null){
                    System.out.println("Course not found");
                }
                else {
                    System.out.println(courseService.findById(2));

                }

                //Finding all courses
                List<Course> courses = courseService.findAll();
                courses.forEach(System.out::println);
            };
        }
    }


