package com.sumerge.springtask.service;

import com.sumerge.springtask.CourseRecommender;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseRepository{

    private CourseRepository courseRepository;
    private CourseRecommender courseRecommender;

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    @Autowired
    public void setCourseRepository(@Qualifier("courseRepo") CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseRecommender getCourseRecommender() {
        return courseRecommender;
    }

    @Autowired
    public void setCourseRecommender(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public void recommendCourse(){
        System.out.println(courseRecommender.recommendCourse());
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }
}
