package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;

import java.util.List;

public interface CourseRepository {
    Course findById(int id);
    List<Course> findAll();
    void save(Course course);
    void delete(Course course);
    void update(Course course);
}
