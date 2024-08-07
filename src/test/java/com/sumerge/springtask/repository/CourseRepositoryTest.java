package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;


    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    void willFindFirstByCourseId() {
        // ARRANGE
        Course givenCourse = new Course("CS Test", "Test CS Course", 8);
        courseRepository.save(givenCourse);
        // ACT
        Long id = 1L;
        Course retrievedCourse = courseRepository.findFirstByCourseId(id);
        // ASSERT
        assertThat(retrievedCourse).isEqualTo(givenCourse);

    }

    @Test
    void existsByCourseName() {
        // ARRANGE
        Course givenCourse = new Course("CS Test", "Test CS Course", 8);
        courseRepository.save(givenCourse);
        // ACT
        boolean exists = courseRepository.existsByCourseName("CS Test");
        assertThat(exists).isTrue();
    }

    @Test
    void doesNotExistByCourseName() {
        // ARRANGE
        Course givenCourse = new Course("CS Test", "Test CS Course", 8);
        courseRepository.save(givenCourse);
        // ACT
        boolean exists = courseRepository.existsByCourseName("Math Test");
        assertThat(exists).isFalse();
    }
}