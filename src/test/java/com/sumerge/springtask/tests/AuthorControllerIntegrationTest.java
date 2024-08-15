package com.sumerge.springtask.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.controller.AuthorController;
import com.sumerge.springtask.dto.AuthorDTO;
import com.sumerge.springtask.mapper.AuthorMapper;
import com.sumerge.springtask.repository.AuthorRepository;
import com.sumerge.springtask.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthorController authorController;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void findingAuthorByEmail() throws Exception {
        // ARRANGE
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Nader");
        authorDTO.setEmail("nader@gmail.com");
        authorDTO.setAuthorBirthdate("1999-4-25");
        authorService.saveAuthor(authorDTO);
        String email = authorRepository.findByEmail("nader@gmail.com").getEmail();
        // ACT
        // ASSERT
        mockMvc.perform(get("/authors/authorByEmail/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorName").value("Nader"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("nader@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorBirthdate").value("1999-4-25"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void findingAuthorThatDoesntExistByEmail() throws Exception {
        // ARRANGE
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Nader");
        authorDTO.setEmail("nader@gmail.com");
        authorDTO.setAuthorBirthdate("1999-4-25");
        authorService.saveAuthor(authorDTO);
        String email = "youssef@gmail.com";
        // ACT
        // ASSERT
        mockMvc.perform(get("/authors/authorByEmail/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Author not found"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addingAnAuthor() throws Exception {
        // ARRANGE
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Nader");
        authorDTO.setEmail("nader@gmail.com");
        authorDTO.setAuthorBirthdate("1999-4-25");
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Author added successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void addingAnAuthorThatAlreadyExists() throws Exception {
        // ARRANGE
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Nader");
        authorDTO.setEmail("nader@gmail.com");
        authorDTO.setAuthorBirthdate("1999-4-25");
        authorService.saveAuthor(authorDTO);
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email is already registered"));
    }

}
