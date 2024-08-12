package com.sumerge.springtask.service;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.exceptions.CourseAlreadyExistsException;
import com.sumerge.springtask.exceptions.NoCoursesAvailableException;
import com.sumerge.springtask.mappers.CourseMapper;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;

    private CourseService courseService;

    private Course course;
    private Course course2;
    private CourseDTO courseDTO;
    private CourseDTO courseDTO2;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository, courseMapper);

        course = new Course();
        course.setCourseName("Test Course");
        course.setCourseDescription("Test Course Description");
        course.setCourseCredit(4);

        courseDTO = new CourseDTO("Test Course", "Test Course Description", 4);
        courseDTO2 = new CourseDTO("Test Course2", "Test Course Description2", 8);

        course2 = new Course();
        course2.setCourseName(courseDTO2.getCourseName());
        course2.setCourseDescription(courseDTO2.getCourseDescription());
        course2.setCourseCredit(courseDTO2.getCourseCredit());



    }


    @Test
    void courseFoundById() {
        // ARRANGE
        Long id = 1L;
        when(courseRepository.findFirstByCourseId(id)).thenReturn(course);
        when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);
        // ACT
        courseService.findById(id);
        // ASSERT
        ArgumentCaptor<Course> capturedCourse = ArgumentCaptor.forClass(Course.class);
        verify(courseMapper).courseToCourseDTO(capturedCourse.capture());

        assertThat(capturedCourse.getValue()).isNotNull();
        assertThat(capturedCourse.getValue()).isEqualTo(course);
    }

    @Test
    void willThrowWhenCourseNotFoundById() {
        // ARRANGE
        Long id = 1L;
        when(courseRepository.findFirstByCourseId(id)).thenReturn(null);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> courseService.findById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Course with id: " + id + " not found");
    }

    @Test
    void foundAllCourses() {
        // ARRANGE
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = new PageImpl<>(Arrays.asList(course, course2), pageable, 2);
        Page<CourseDTO> convertedCourses = new PageImpl<>(Arrays.asList(courseDTO, courseDTO2), pageable, 2);
        when(courseRepository.findAll(pageable)).thenReturn(courses);
        when(courseMapper.courseToCourseDTO(courses.getContent().get(0))).thenReturn(convertedCourses.getContent()
                .get(0));
        when(courseMapper.courseToCourseDTO(courses.getContent().get(1))).thenReturn(convertedCourses.getContent()
                .get(1));
        // ACT
        Page<CourseDTO> result = courseService.findAll(page, size);
        // ASSERT
        ArgumentCaptor<Pageable> capturedPage = ArgumentCaptor.forClass(Pageable.class);
        verify(courseRepository).findAll(capturedPage.capture());

        assertThat(capturedPage.getValue()).isNotNull();
        assertThat(capturedPage.getValue()).isEqualTo(pageable);
        assertThat(result).isEqualTo(convertedCourses);
        assertThat(result.getContent().get(0)).isEqualTo(convertedCourses.getContent().get(0));
        assertThat(result.getContent().get(1)).isEqualTo(convertedCourses.getContent().get(1));
    }

    @Test
    void willThrowWhenDidNotFindAllCourses() {
        // ARRANGE
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = new PageImpl<>(Arrays.asList(), pageable, 0);
        when(courseRepository.findAll(pageable)).thenReturn(courses);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> courseService.findAll(page, size)).isInstanceOf(NoCoursesAvailableException.class)
                .hasMessageContaining("No courses available");

    }

    @Test
    void savingANonExistingCourse() {
        // ARRANGE
        when(courseRepository.existsByCourseName(courseDTO.getCourseName())).thenReturn(false);
        when(courseMapper.courseDtoToCourse(courseDTO)).thenReturn(course);
        //ACT
        courseService.save(courseDTO);
        // ASSERT
        ArgumentCaptor<Course> capturedCourse = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(capturedCourse.capture());

        assertThat(capturedCourse.getValue()).isNotNull();
        assertThat(capturedCourse.getValue()).isEqualTo(course);
    }

    @Test
    void savingAnExistingCourse() {
        // ARRANGE
        when(courseRepository.existsByCourseName(courseDTO.getCourseName())).thenReturn(true);
        // ASSERT
        assertThatThrownBy(() -> courseService.save(courseDTO))
                .isInstanceOf(CourseAlreadyExistsException.class)
                .hasMessageContaining("This course already exists");
    }

    @Test
    void deletingAnExistingCourse() {
        // ARRANGE
        Long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);
        // ACT
        courseService.delete(id);
        // ASSERT
        ArgumentCaptor<Long> capturedId = ArgumentCaptor.forClass(Long.class);
        verify(courseRepository).deleteById(capturedId.capture());

        assertThat(capturedId.getValue()).isNotNull();
        assertThat(capturedId.getValue()).isEqualTo(id);

    }

    @Test
    void deletingANonExistingCourse() {
        // ARRANGE
        Long id = 4L;
        when(courseRepository.existsById(id)).thenReturn(false);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> courseService.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Course with id: " + id + " not found");

    }

    @Test
    void updateAnExistingCourse() {
        // ARRANGE
        Long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);
        when(courseMapper.courseDtoToCourse(courseDTO2)).thenReturn(course2);
        when(courseRepository.findFirstByCourseId(id)).thenReturn(course);
        // ACT
        courseService.update(id, courseDTO2);
        // ASSERT
        ArgumentCaptor<Course> capturedCourse = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(capturedCourse.capture());

        assertThat(capturedCourse.getValue()).isNotNull();
        assertThat(capturedCourse.getValue().getCourseName()).isEqualTo(course2.getCourseName());
        assertThat(capturedCourse.getValue().getCourseDescription()).isEqualTo(course2.getCourseDescription());
        assertThat(capturedCourse.getValue().getCourseCredit()).isEqualTo(course2.getCourseCredit());
        assertThat(courseRepository.findFirstByCourseId(id).getCourseName()).isEqualTo(course2.getCourseName());
        assertThat(courseRepository.findFirstByCourseId(id)
                .getCourseDescription()).isEqualTo(course2.getCourseDescription());
        assertThat(courseRepository.findFirstByCourseId(id).getCourseCredit()).isEqualTo(course2.getCourseCredit());
    }

    @Test
    void updateANonExistingCourse() {
        // ARRANGE
        Long id = 4L;
        when(courseRepository.existsById(id)).thenReturn(false);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> courseService.update(id, courseDTO2))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Course with id: " + id + " not found");
    }
}