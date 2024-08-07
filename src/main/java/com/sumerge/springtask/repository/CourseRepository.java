package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findFirstByCourseId(Long id);
    boolean existsByCourseName(String courseName);
}
