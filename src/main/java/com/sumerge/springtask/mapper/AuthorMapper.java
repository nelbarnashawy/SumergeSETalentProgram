package com.sumerge.springtask.mapper;

import com.sumerge.springtask.dto.AuthorDTO;
import com.sumerge.springtask.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO authorToAuthorDto(Author author);
    Author authorDtoToAuthor(AuthorDTO authorDTO);
}
