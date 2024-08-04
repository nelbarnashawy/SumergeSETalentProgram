package com.sumerge.springtask.controller;

import com.sumerge.springtask.repository.CourseRepositoryImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @GetMapping("/")
    public String home() {
        return "Hello User";
    }

    @GetMapping("/recommendedCourses")
    public String recommendedCourses() {
        return new CourseRepositoryImp().findAll().toString();
    }
}
