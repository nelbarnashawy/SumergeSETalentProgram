package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.model.Author;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    private final AuthorMapper authorMapper = new AuthorMapperImpl();

    @Test
    void convertAuthortoAuthorDTO() {
        // ARRANGE
        Author author = new Author("Nader", "nader@gmail.com", new Date(1999, 4, 25));
        // ACT
        AuthorDTO authorDTO = authorMapper.authorToAuthorDto(author);
        // ASSERT
        assertThat(authorDTO).isNotNull();
        assertThat(authorDTO.getAuthorName()).isEqualTo(author.getAuthorName());
        assertThat(authorDTO.getEmail()).isEqualTo(author.getEmail());
        assertThat(authorDTO.getAuthorBirthdate()).isEqualTo(author.getAuthorBirthdate());
    }

    @Test
    void convertAuthorDTOtoAuthor() {
        // ARRANGE
        AuthorDTO authorDTO = new AuthorDTO("Youssef", "youssef@gmail.com", new Date(2000, 8, 28));
        // ACT
        Author author = authorMapper.authorDtoToAuthor(authorDTO);
        // ASSERT
        assertThat(author).isNotNull();
        assertThat(author.getAuthorName()).isEqualTo(authorDTO.getAuthorName());
        assertThat(author.getEmail()).isEqualTo(authorDTO.getEmail());
        assertThat(author.getAuthorBirthdate()).isEqualTo(authorDTO.getAuthorBirthdate());
    }
}