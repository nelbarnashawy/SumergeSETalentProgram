package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        // ARRANGE
        Author givenAuthor = new Author("Nader", "nader@gmail.com", "1999-4-25");
        authorRepository.save(givenAuthor);
        // ACT
        Author foundAuthor = authorRepository.findByEmail("nader@gmail.com");
        // ASSERT
        assertThat(foundAuthor).isEqualTo(givenAuthor);

    }

    @Test
    void notFoundByEmail() {
        // ARRANGE
        Author givenAuthor = new Author("Nader", "nader@gmail.com", "1999-4-25");
        authorRepository.save(givenAuthor);
        // ACT
        Author foundAuthor = authorRepository.findByEmail("youssef@gmail.com");
        // ASSERT
        assertThat(foundAuthor).isNotEqualTo(givenAuthor);

    }

    @Test
    void existsByEmail() {
        // ARRANGE
        Author givenAuthor = new Author("Nader", "nader@gmail.com", "1999-4-25");
        authorRepository.save(givenAuthor);
        // ACT
        boolean exists = authorRepository.existsByEmail("nader@gmail.com");
        // ASSERT
        assertThat(exists).isTrue();
    }

    @Test
    void doesNotExistByEmail() {
        // ARRANGE
        Author givenAuthor = new Author("Nader", "nader@gmail.com", "1999-4-25");
        authorRepository.save(givenAuthor);
        // ACT
        boolean exists = authorRepository.existsByEmail("youssef@gmail.com");
        // ASSERT
        assertThat(exists).isFalse();
    }
}