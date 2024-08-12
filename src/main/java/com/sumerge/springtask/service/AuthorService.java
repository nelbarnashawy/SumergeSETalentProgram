package com.sumerge.springtask.service;

import com.sumerge.springtask.dto.AuthorDTO;
import com.sumerge.springtask.exception.AuthorAlreadyExistsException;
import com.sumerge.springtask.mapper.AuthorMapper;
import com.sumerge.springtask.model.Author;
import com.sumerge.springtask.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    public void saveAuthor(AuthorDTO authorDTO) {
        if(authorRepository.existsByEmail(authorDTO.getEmail())){
            throw new AuthorAlreadyExistsException("Email is already registered");
        }
        Author author = authorMapper.authorDtoToAuthor(authorDTO);
        authorRepository.save(author);
    }

    public AuthorDTO findAuthorByEmail(String email) {
        if(!authorRepository.existsByEmail(email)){
            throw new EntityNotFoundException("Author not found");
        }

        return authorMapper.authorToAuthorDto(authorRepository.findByEmail(email));
    }
}
