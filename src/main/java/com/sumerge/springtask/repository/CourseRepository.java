package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;

import java.util.List;

public interface CourseRepository {
    Course findById(int id);
    List<Course> findAll();
    void save(Course course);
    void delete(int id);
    void update(int id, Course course);
}
