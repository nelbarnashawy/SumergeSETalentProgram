package com.sumerge.springtask.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.dto.CourseDTO;
import com.sumerge.springtask.repository.CourseRepository;
import com.sumerge.springtask.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration-test")
class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
        courseRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void findingNoCourses() throws Exception {
        // ARRANGE
        int page = 0;
        int size = 10;
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/discover/page={page}&size={size}", page, size)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").doesNotExist())
                .andExpect(content().string("No courses available"));
    }


    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addNewCourseAndFindingCourseById() throws Exception {
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

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void findCourseById() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Math");
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        courseService.save(courseDTO);
        Long id = courseRepository.findFirstByCourseName("Math").getCourseId();
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/view/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Math"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addNewExistingCourse() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Math");
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        courseService.save(courseDTO);
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isConflict())
                .andExpect(content().string("This course already exists"));

    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addCourseWithNullValues() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        // ACT
        // ASSERT
        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void findingAllCourses() throws Exception {
        // ARRANGE
        int page = 0;
        int size = 10;
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Math");
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        courseService.save(courseDTO);
        CourseDTO courseDTO2 = new CourseDTO();
        courseDTO2.setCourseName("Physics");
        courseDTO2.setCourseDescription("Physics Course");
        courseDTO2.setCourseCredit(2);
        courseService.save(courseDTO2);
        // ACT
        // ASSERT
        mockMvc.perform(get("/courses/discover/page={page}&size={size}", page, size)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].courseName").value("Math"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].courseDescription").value("Math Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].courseCredit").value(8));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void updateACourse() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Physics");
        courseDTO.setCourseDescription("Physics Course");
        courseDTO.setCourseCredit(2);
        courseService.save(courseDTO);
        Long id = courseRepository.findFirstByCourseName("Physics").getCourseId();
        CourseDTO updatedCourse = new CourseDTO();
        updatedCourse.setCourseName("Math");
        updatedCourse.setCourseDescription("Math Test");
        updatedCourse.setCourseCredit(8);
        // ACT
        // ASSERT
        mockMvc.perform(put("/courses/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCourse))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Course updated successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void updateACourseThatDoesntExist() throws Exception {
        // ARRANGE
        Long id = 100L;
        CourseDTO updatedCourse = new CourseDTO();
        updatedCourse.setCourseName("Math");
        updatedCourse.setCourseDescription("Math Test");
        updatedCourse.setCourseCredit(8);
        // ACT
        // ASSERT
        mockMvc.perform(put("/courses/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCourse))
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with id: " + id + " not found"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void deleteACourse() throws Exception {
        // ARRANGE
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName("Math");
        courseDTO.setCourseDescription("Math Test");
        courseDTO.setCourseCredit(8);
        courseService.save(courseDTO);
        Long id = courseRepository.findFirstByCourseName("Math").getCourseId();
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
    void deleteACourseThatDoesntExist() throws Exception {
        // ARRANGE
        Long id = 100L;
        // ACT
        // ASSERT
        mockMvc.perform(delete("/courses/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with id: " + id + " not found"));
    }

}
