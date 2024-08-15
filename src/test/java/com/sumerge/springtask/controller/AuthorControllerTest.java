package com.sumerge.springtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.dto.AuthorDTO;
import com.sumerge.springtask.exception.AuthorAlreadyExistsException;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setAuthorName("Nader");
        author.setEmail("nader@gmail.com");
        author.setAuthorBirthdate("1999-4-25");

        authorDTO = new AuthorDTO();
        authorDTO.setAuthorName(author.getAuthorName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAuthorBirthdate(author.getAuthorBirthdate());
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingFindingAuthorByEmail() throws Exception {
        // ARRANGE
        String email = "nader@gmail.com";
        when(authorService.findAuthorByEmail(email)).thenReturn(authorDTO);
        // ACT
        // ASSERT
        mockMvc.perform(get("/authors/authorByEmail/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorName").value("Nader"))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"USER", "ADMIN"})
    void testingAddingAnAuthor() throws Exception {
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isOk())
                .andExpect(content().string("Author added successfully"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"USER", "ADMIN"})
    void testingAddingAnAuthorThatAlreadyExists() throws Exception {
        // ARRANGE
        doThrow(new AuthorAlreadyExistsException("Email is already registered")).when(authorService)
                .saveAuthor(any(AuthorDTO.class));
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDTO))
                        .header("x-validation-report", true))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email is already registered"));
    }

    @Test
    @WithMockUser(username = "testAdmin", password = "testAdmin", roles = {"ADMIN"})
    void testingAddingAnAuthorWithNullValues() throws Exception {
        // ARRANGE
        AuthorDTO authorDtoNull = new AuthorDTO();
        authorDtoNull.setAuthorName("Nader");
        authorDtoNull.setAuthorBirthdate("1999-4-25");
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDtoNull))
                        .header("x-validation-report", true))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is required"));
    }
}