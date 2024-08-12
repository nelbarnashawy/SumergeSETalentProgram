package com.sumerge.springtask.service;

import com.sumerge.springtask.dto.CourseDTO;
import com.sumerge.springtask.exception.CourseAlreadyExistsException;
import com.sumerge.springtask.exception.NoCoursesAvailableException;
import com.sumerge.springtask.mapper.CourseMapper;
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
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public CourseDTO findById(Long id) throws EntityNotFoundException {
        Course course = courseRepository.findFirstByCourseId(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        return courseMapper.courseToCourseDTO(course);
    }

    public Page<CourseDTO> findAll(int page, int size) throws NoCoursesAvailableException {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseRepository.findAll(pageable);
        if (courses.isEmpty()) {
            throw new NoCoursesAvailableException("No courses available");
        }
        return courses.map(courseMapper::courseToCourseDTO);
    }

    public void save(CourseDTO courseDTO) throws CourseAlreadyExistsException {
        if (courseRepository.existsByCourseName(courseDTO.getCourseName())) {
            throw new CourseAlreadyExistsException("This course already exists");
        }
        Course course = courseMapper.courseDtoToCourse(courseDTO);
        courseRepository.save(course);
    }

    public void delete(Long id) throws EntityNotFoundException {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    public void update(Long id, CourseDTO courseDTO) throws EntityNotFoundException {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course with id: " + id + " not found");
        }
        Course course = courseMapper.courseDtoToCourse(courseDTO);
        Course toUpdate = courseRepository.findFirstByCourseId(id);
        toUpdate.setCourseName(course.getCourseName());
        toUpdate.setCourseDescription(course.getCourseDescription());
        toUpdate.setCourseCredit(course.getCourseCredit());
        courseRepository.save(toUpdate);
    }
}
