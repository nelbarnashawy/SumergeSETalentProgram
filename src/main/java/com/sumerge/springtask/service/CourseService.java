package com.sumerge.springtask.service;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.exceptions.CourseAlreadyExistsException;
import com.sumerge.springtask.exceptions.NoCoursesAvailableException;
import com.sumerge.springtask.mappers.CourseMapper;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDTO getCourseDTO(Course course){
        return courseMapper.CoursetoCourseDTO(course);
    }

    public Course getCourse(CourseDTO courseDTO){
        return courseMapper.CourseDTOtoCourse(courseDTO);
    }

    public CourseDTO findById(Long id) {
        Course course = courseRepository.findFirstByCourseId(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        return getCourseDTO(course);
    }

    public Page<CourseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseRepository.findAll(pageable);
        if (courses.isEmpty()) {
            throw new NoCoursesAvailableException("No courses available");
        }
        return courses.map(this::getCourseDTO);
    }

    public void save(CourseDTO courseDTO) {
        if(courseRepository.existsByCourseName(courseDTO.getCourseName())){
            throw new CourseAlreadyExistsException("This course already exists");
        }
        Course course = getCourse(courseDTO);
        courseRepository.save(course);
    }

    public void delete(Long id) {
        if(!courseRepository.existsById(id)){
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    public void update(Long id, CourseDTO courseDTO) {
        if(!courseRepository.existsById(id)){
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        Course course = getCourse(courseDTO);
        Course toUpdate = courseRepository.findFirstByCourseId(id);
        toUpdate.setCourseName(course.getCourseName());
        toUpdate.setCourseDescription(course.getCourseDescription());
        toUpdate.setCourseCredit(course.getCourseCredit());
        courseRepository.save(toUpdate);
    }
}
