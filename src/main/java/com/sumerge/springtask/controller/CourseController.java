package com.sumerge.springtask.controller;

import com.sumerge.springtask.repository.CourseRepositoryImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @RequestMapping("/recommendedCourses")
    public String recommendedCourses() {
        return new CourseRepositoryImp().findAll().toString();
    }
}
