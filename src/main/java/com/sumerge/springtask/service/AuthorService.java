package com.sumerge.springtask.service;

import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.exceptions.AuthorAlreadyExistsException;
import com.sumerge.springtask.mappers.AuthorMapper;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public AuthorDTO getAuthorDTO(Author author) {
        return authorMapper.toAuthorDTO(author);
    }

    public Author getAuthor(AuthorDTO authorDTO) {
        return authorMapper.toAuthor(authorDTO);
    }

    public void saveAuthor(AuthorDTO authorDTO) {
        if(authorRepository.existsByEmail(authorDTO.getEmail())){
            throw new AuthorAlreadyExistsException("Email is already registered");
        }
        Author author = authorMapper.toAuthor(authorDTO);
        authorRepository.save(author);
    }

    public AuthorDTO findAuthorByEmail(String email) {
        return getAuthorDTO(authorRepository.findByEmail(email));
    }
}
