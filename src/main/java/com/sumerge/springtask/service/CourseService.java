package com.sumerge.springtask.service;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.mappers.CourseMapper;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CourseService {

    private CourseRepository courseRepository;
    //private CourseRecommender courseRecommender;

    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public CourseDTO getCourseDTO(Course course){
        return courseMapper.CoursetoCourseDTO(course);
    }

    public Course getCourse(CourseDTO courseDTO){
        return courseMapper.CourseDTOtoCourse(courseDTO);
    }

    public CourseDTO findById(Long id) {
        Course course = courseRepository.findFirstById(id);
        return getCourseDTO(course);
    }

    public Page<CourseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(this::getCourseDTO);
    }

    public void save(CourseDTO courseDTO) {
        Course course = getCourse(courseDTO);
        courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public void update(Long id, CourseDTO courseDTO) {
        Course course = getCourse(courseDTO);
        Course toUpdate = courseRepository.findFirstById(id);
        toUpdate.setCourse_name(course.getCourse_name());
        toUpdate.setCourse_description(course.getCourse_description());
        toUpdate.setCourse_credit(course.getCourse_credit());
        courseRepository.save(toUpdate);
    }
}
