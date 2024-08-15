package com.sumerge.springtask.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.controller.CourseController;
import com.sumerge.springtask.dto.CourseDTO;
import com.sumerge.springtask.mapper.CourseMapper;
import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import com.sumerge.springtask.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringTaskApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CourseController courseController;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addNewCourse() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Math");
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Course added successfully"));

    }

}
