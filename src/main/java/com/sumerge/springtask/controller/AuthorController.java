package com.sumerge.springtask.controller;

import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authorByEmail/{email}")
    public ResponseEntity<AuthorDTO> authorByEmail(@PathVariable String email) {
        return ResponseEntity.ok(authorService.findAuthorByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        authorService.saveAuthor(authorDTO);
        return ResponseEntity.ok("Author added successfully");
    }

}

