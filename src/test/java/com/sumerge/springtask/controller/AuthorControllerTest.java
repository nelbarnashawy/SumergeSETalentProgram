package com.sumerge.springtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.exceptions.AuthorAlreadyExistsException;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthorController.class)
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
    void testingFindingAuthorByEmail() throws Exception {
        // ARRANGE
        String email = "nader@gmail.com";
        when(authorService.findAuthorByEmail(email)).thenReturn(authorDTO);
        // ACT
        // ASSERT
        mockMvc.perform(get("/authors/authorByEmail/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorName").value("Nader"))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    void testingAddingAnAuthor() throws Exception {
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Author added successfully"));
    }

    @Test
    void testingAddingAnAuthorThatAlreadyExists() throws Exception {
        // ARRANGE
        doThrow(new AuthorAlreadyExistsException("Email is already registered")).when(authorService).saveAuthor(any(AuthorDTO.class));
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDTO)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email is already registered"));
    }

    @Test
    void testingAddingAnAuthorWithNullValues() throws Exception {
        // ARRANGE
        AuthorDTO authorDtoNull = new AuthorDTO();
        authorDtoNull.setAuthorName("Nader");
        authorDtoNull.setAuthorBirthdate("1999-4-25");
        // ACT
        // ASSERT
        mockMvc.perform(post("/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authorDtoNull)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is required"));
    }
}