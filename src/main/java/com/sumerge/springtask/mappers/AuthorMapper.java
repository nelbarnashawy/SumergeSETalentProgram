package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.AuthorDTO;
import com.sumerge.springtask.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO authorToAuthorDto(Author author);
    Author authorDtoToAuthor(AuthorDTO authorDTO);
}
