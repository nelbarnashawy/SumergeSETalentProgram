package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByEmail(String email);
}