package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findFirstById(Long id);
}
