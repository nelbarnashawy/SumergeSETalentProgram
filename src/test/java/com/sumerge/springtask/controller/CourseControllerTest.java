package com.sumerge.springtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CourseController.class)
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
        courseDTO2.setCourseName("CS");
        courseDTO2.setCourseDescription("CS Course");
        courseDTO2.setCourseCredit(8);
    }

    @Test
    void testingViewById() throws Exception {
        // ARRANGE
        Long id = 1L;
        when(courseService.findById(anyLong())).thenReturn(courseDTO);
        // ACT
        // ASSERT
        mockMvc.perform(get("/course/view/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Math"));
    }

    @Test
    void testingAddingACourse() throws Exception {
        // ACT
        // ASSERT
        mockMvc.perform(post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Course added successfully"));
    }

    @Test
    void testingAddingACourseWithNullValues() throws Exception {
        // ARRANGE
        CourseDTO courseDtoNull = new CourseDTO();
        course.setCourseDescription("Physics Course");
        course.setCourseCredit(2);
        // ACT
        // ASSERT
        mockMvc.perform(post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDtoNull)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));
    }

    @Test
    void testingUpdatingACourse() throws Exception {
        // ARRANGE
        Long id = 1L;
        // ACT
        // ASSERT
        mockMvc.perform(put("/course/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDTO2)))
                .andExpect(status().isOk())
                .andExpect(content().string("Course updated successfully"));
    }

    @Test
    void testingUpdatingACourseWithAnotherCourseWithNullValues() throws Exception {
        // ARRANGE
        Long id = 1L;
        CourseDTO courseDtoNull = new CourseDTO();
        course.setCourseDescription("Physics Course");
        course.setCourseCredit(2);
        // ACT
        // ASSERT
        mockMvc.perform(put("/course/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(courseDtoNull)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));
    }

    @Test
    void testingDeletingACourse() throws Exception {
        // ARRANGE
        Long id = 1L;
        // ACT
        // ASSERT
        mockMvc.perform(delete("/course/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Course deleted successfully"));
    }

    @Test
    void testingFindingAllCourses() throws Exception {
        // ARRANGE
        int page = 0;
        int size = 10;
        List<CourseDTO> courseDTOList = Arrays.asList(courseDTO, courseDTO2);
        Page<CourseDTO> courseDTOPage = new PageImpl<>(courseDTOList);
        when(courseService.findAll(0, 10)).thenReturn(courseDTOPage);
        // ACT
        // ASSERT
        mockMvc.perform(get("/course/discover/page={page}&size={size}", page, size)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value("1"))
                .andExpect(jsonPath("$.totalElements").value("2"))
                .andExpect(jsonPath("$.number").value("0"))
                .andExpect(jsonPath("$.size").value("2"))
                .andExpect(jsonPath("$.content[0].courseName").value("Math"))
                .andExpect(jsonPath("$.content[1].courseName").value("CS"));
    }
}