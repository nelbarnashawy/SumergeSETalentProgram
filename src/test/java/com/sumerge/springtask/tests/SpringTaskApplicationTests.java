package com.sumerge.springtask.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.controller.CourseController;
import com.sumerge.springtask.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(CourseController.class)
				.build();
		this.objectMapper = new ObjectMapper();
	}

	@Test
	void addNewCourse() throws  Exception{
		// ARRANGE
		Course course = new Course();
		course.setCourseName("Math");
		course.setCourseDescription("Math Test");
		course.setCourseCredit(8);
		// ACT
		// ASSERT
		mockMvc.perform(post("/courses/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(course)))
				.andExpect(status().isOk())
				.andExpect(content().string("Course added successfully"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Math"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.courseDescription").value("Math Test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.courseCredit").value(8));

	}

}
