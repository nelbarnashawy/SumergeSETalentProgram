package com.sumerge.springtask.controller;

import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return courseService.findById(id).toString();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void add(@RequestBody Course course) {
        courseService.save(course);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody Course updatedCourse) {
        courseService.update(id, updatedCourse);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id, @RequestBody Course course) {
        courseService.delete(id, course);
    }

    @GetMapping("/discover")
    public String recommendedCourses() {
        List<Course> courses = courseService.recommendCourse();
        return courses.toString();
    }
}
