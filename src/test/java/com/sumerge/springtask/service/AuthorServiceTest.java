package com.sumerge.springtask.service;

import com.sumerge.springtask.dto.AuthorDTO;
import com.sumerge.springtask.exception.AuthorAlreadyExistsException;
import com.sumerge.springtask.mapper.AuthorMapper;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;

    private AuthorService authorService;

    private Author author;
    private Author author2;
    private AuthorDTO authorDTO;
    private AuthorDTO authorDTO2;

    @BeforeEach
    void setUp() {
        authorService = new AuthorService(authorMapper, authorRepository);

        author = new Author();
        author.setAuthorName("Nader");
        author.setEmail("nader@gmail.com");
        author.setAuthorBirthdate("1999-4-25");

        author2 = new Author("Youssef", "youssef@gmail.com", "2000-8-28");

        authorDTO = new AuthorDTO("Nader", "nader@gmail.com", "1999-4-25");
        authorDTO2 = new AuthorDTO("Youssef", "youssef@gmail.com", "2000-8-28");
    }

    @Test
    void savingANonExistingAuthor() {
        // ARRANGE
        String email = "youssef@gmail.com";
        when(authorRepository.existsByEmail(email)).thenReturn(false);
        when(authorMapper.authorDtoToAuthor(authorDTO2)).thenReturn(author2);
        // ACT
        authorService.saveAuthor(authorDTO2);
        // ASSERT
        ArgumentCaptor<Author> capturedAuthor = ArgumentCaptor.forClass(Author.class);
        verify(authorRepository).save(capturedAuthor.capture());

        assertThat(capturedAuthor.getValue()).isNotNull();
        assertThat(capturedAuthor.getValue()).isEqualTo(author2);
        assertThat(capturedAuthor.getValue().getAuthorName()).isEqualTo(authorDTO2.getAuthorName());
        assertThat(capturedAuthor.getValue().getEmail()).isEqualTo(authorDTO2.getEmail());
        assertThat(capturedAuthor.getValue().getAuthorBirthdate()).isEqualTo(authorDTO2.getAuthorBirthdate());

    }

    @Test
    void savingAnExistingAuthor() {
        // ARRANGE
        String email = "nader@gmail.com";
        when(authorRepository.existsByEmail(email)).thenReturn(true);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> authorService.saveAuthor(authorDTO))
                .isInstanceOf(AuthorAlreadyExistsException.class)
                .hasMessageContaining("Email is already registered");
    }

    @Test
    void authorFoundByEmail() {
        // ARRANGE
        String email = "nader@gmail.com";
        when(authorRepository.existsByEmail(email)).thenReturn(true);
        when(authorRepository.findByEmail(email)).thenReturn(author);
        when(authorMapper.authorToAuthorDto(author)).thenReturn(authorDTO);
        // ACT
        authorService.findAuthorByEmail(email);
        // ASSERT
        ArgumentCaptor<String> capturedEmail = ArgumentCaptor.forClass(String.class);
        verify(authorRepository).findByEmail(capturedEmail.capture());
        ArgumentCaptor<Author> capturedAuthor = ArgumentCaptor.forClass(Author.class);
        verify(authorMapper).authorToAuthorDto(capturedAuthor.capture());

        assertThat(capturedEmail.getValue()).isNotNull();
        assertThat(capturedEmail.getValue()).isEqualTo(email);

        assertThat(capturedAuthor.getValue()).isNotNull();
        assertThat(capturedAuthor.getValue().getAuthorName()).isEqualTo(authorDTO.getAuthorName());
        assertThat(capturedAuthor.getValue().getEmail()).isEqualTo(authorDTO.getEmail());
        assertThat(capturedAuthor.getValue().getAuthorBirthdate()).isEqualTo(authorDTO.getAuthorBirthdate());
    }

    @Test
    void authorNotFoundByEmail() {
        // ARRANGE
        String email = "nader@gmail.com";
        when(authorRepository.existsByEmail(email)).thenReturn(false);
        // ACT
        // ASSERT
        assertThatThrownBy(() -> authorService.findAuthorByEmail(email))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Author not found");
    }
}