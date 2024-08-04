package com.sumerge.springtask.controller;

import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello User";
    }

    @GetMapping("/view/{id}")
    public String viewById(@PathVariable int id) {
        try {
            return courseService.findById(id).toString();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found!");
        }
    }

    @PostMapping("/add")
    public void add(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody Course updatedCourse) {
        try {
            courseService.update(id, updatedCourse);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!");
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        try {
            courseService.delete(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found!");
        }
    }

    @GetMapping("/discover")
    public String recommendedCourses() {
        List<Course> courses = courseService.recommendCourse();
        return courses.toString();
    }

}
