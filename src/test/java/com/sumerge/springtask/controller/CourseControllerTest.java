package com.sumerge.springtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.dto.CourseDTO;
import com.sumerge.springtask.exception.CourseAlreadyExistsException;
import com.sumerge.springtask.exception.NoCoursesAvailableException;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    @MockBean
    private CourseService courseService;
    @Autowired
    private MockMvc mockMvc;

    private Course course;
    private Course course2;

    private CourseDTO courseDTO;
    private CourseDTO courseDTO2;

    @BeforeEach
    void setUp() {
        course = new Course("Math", "Math Test", 8);
        course2 = new Course();
        course2.setCourseName("CS");
        course2.setCourseDescription("CS Course");
        course2.setCourseCredit(8);

        courseDTO = new CourseDTO("Math", "Math Test", 8);
        courseDTO2 = new CourseDTO();
        courseDTO2.setCourseName(course2.getCourseName());
        courseDTO2.setCourseDescription(course2.getCourseDescription());
        courseDTO2.setCourseCredit(course2.getCourseCredit());
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingViewById() throws Exception {
        // ARRANGE
        Long id = 1L;
        when(courseService.findById(id)).thenReturn(courseDTO);
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/view/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Math"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingViewByNonExistingId() throws Exception {
        // ARRANGE
        Long id = 3L;
        when(courseService.findById(id)).thenThrow(new EntityNotFoundException("Course with id: " + id + " not found"));
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/view/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with id: " + id + " not found"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingAddingACourse() throws Exception {
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Course added successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingAddingACourseWithNullValues() throws Exception {
        // ARRANGE
        CourseDTO courseDtoNull = new CourseDTO();
        course.setCourseDescription("Physics Course");
        course.setCourseCredit(2);
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDtoNull))
                        .header("x-validation-report", true))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingAddingACourseThatAlreadyExists() throws Exception {
        // ARRANGE
        doThrow(new CourseAlreadyExistsException("This course already exists")).when(courseService)
                .save(any(CourseDTO.class));
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isConflict())
                .andExpect(content().string("This course already exists"));
    }


    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingUpdatingACourse() throws Exception {
        // ARRANGE
        Long id = 1L;
        // ACT
        // ASSERT
        mockMvc.perform(put("/courses/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDTO2))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Course updated successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingUpdatingACourseWithAnotherCourseWithNullValues() throws Exception {
        // ARRANGE
        Long id = 1L;
        CourseDTO courseDtoNull = new CourseDTO();
        course.setCourseDescription("Physics Course");
        course.setCourseCredit(2);
        // ACT
        // ASSERT
        mockMvc.perform(put("/courses/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDtoNull))
                        .header("x-validation-report", true))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingDeletingACourse() throws Exception {
        // ARRANGE
        Long id = 1L;
        // ACT
        // ASSERT
        mockMvc.perform(delete("/courses/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Course deleted successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingFindingAllCourses() throws Exception {
        // ARRANGE
        int page = 0;
        int size = 10;
        List<CourseDTO> courseDTOList = Arrays.asList(courseDTO, courseDTO2);
        Page<CourseDTO> courseDTOPage = new PageImpl<>(courseDTOList);
        when(courseService.findAll(0, 10)).thenReturn(courseDTOPage);
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/discover/page={page}&size={size}", page, size)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value("1"))
                .andExpect(jsonPath("$.totalElements").value("2"))
                .andExpect(jsonPath("$.number").value("0"))
                .andExpect(jsonPath("$.size").value("2"))
                .andExpect(jsonPath("$.content[0].courseName").value("Math"))
                .andExpect(jsonPath("$.content[1].courseName").value("CS"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingFindingAllCoursesWithNoExistingCourses() throws Exception {
        // ARRANGE
        int page = 0;
        int size = 10;
        when(courseService.findAll(0, 10)).thenThrow(new NoCoursesAvailableException("No courses available"));
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/discover/page={page}&size={size}", page, size)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No courses available"));
    }
}