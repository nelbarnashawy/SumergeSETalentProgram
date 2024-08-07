package com.sumerge.springtask.controller;

import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String home() {
        return "Authors page";
    }

    @GetMapping("/authorByEmail/{email}")
    public AuthorDTO authorByEmail(@PathVariable String email) {
        return authorService.findAuthorByEmail(email);
    }

    @PostMapping("/add")
    public void addAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.saveAuthor(authorDTO);
    }

}

