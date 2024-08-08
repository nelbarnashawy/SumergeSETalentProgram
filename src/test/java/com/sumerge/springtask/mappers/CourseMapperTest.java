package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.model.Course;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {

    private final CourseMapper courseMapper = new CourseMapperImpl();

    @Test
    void convertCourseToCourseDTO() {
        // ARRANGE
        Course course = new Course("Math Test", "Test Math Course", 8);
        // ACT
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);
        // ASSERT
        assertThat(courseDTO).isNotNull();
        assertThat(courseDTO.getCourseName()).isEqualTo(course.getCourseName());
        assertThat(courseDTO.getCourseDescription()).isEqualTo(course.getCourseDescription());
        assertThat(courseDTO.getCourseCredit()).isEqualTo(course.getCourseCredit());
    }

    @Test
    void convertCourseDtoToCourse() {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO("Math Test", "Test Math Course", 8);
        // ACT
        Course course = courseMapper.courseDtoToCourse(courseDTO);
        // ASSERT
        assertThat(course).isNotNull();
        assertThat(course.getCourseName()).isEqualTo(courseDTO.getCourseName());
        assertThat(course.getCourseDescription()).isEqualTo(courseDTO.getCourseDescription());
        assertThat(course.getCourseCredit()).isEqualTo(courseDTO.getCourseCredit());
    }
}