package com.sumerge.springtask.controller;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.service.CourseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Course ID not found")
    })
    public CourseDTO viewById(@PathVariable Long id) {
            return courseService.findById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CourseDTO courseDTO) {
        courseService.save(courseDTO);
    }

    @PutMapping("/update/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Course ID not found")
    })
    public void update(@PathVariable Long id, @RequestBody CourseDTO updatedCourse) {
            courseService.update(id, updatedCourse);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
            courseService.delete(id);
    }

    @GetMapping("/discover/page={page}&size={size}")
    public Page<CourseDTO> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return courseService.findAll(page, size);
    }

}
