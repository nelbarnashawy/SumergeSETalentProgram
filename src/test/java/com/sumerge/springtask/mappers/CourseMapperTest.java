package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {

    private final CourseMapper courseMapper = new CourseMapperImpl();

    @Test
    void convertCoursetoCourseDTO() {
        // ARRANGE
        Course course = new Course("Math Test", "Test Math Course", 8);
        // ACT
        CourseDTO courseDTO = courseMapper.CoursetoCourseDTO(course);
        // ASSERT
        assertThat(courseDTO).isNotNull();
        assertThat(courseDTO.getCourseName()).isEqualTo(course.getCourseName());
        assertThat(courseDTO.getCourseDescription()).isEqualTo(course.getCourseDescription());
        assertThat(courseDTO.getCourseCredit()).isEqualTo(course.getCourseCredit());
    }

    @Test
    void convertCourseDTOtoCourse() {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO("Math Test", "Test Math Course", 8);
        // ACT
        Course course = courseMapper.CourseDTOtoCourse(courseDTO);
        // ASSERT
        assertThat(course).isNotNull();
        assertThat(course.getCourseName()).isEqualTo(courseDTO.getCourseName());
        assertThat(course.getCourseDescription()).isEqualTo(courseDTO.getCourseDescription());
        assertThat(course.getCourseCredit()).isEqualTo(courseDTO.getCourseCredit());
    }
}