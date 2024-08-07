package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByEmail(String email);
    boolean existsByEmail(String email);
}