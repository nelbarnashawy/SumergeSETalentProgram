package com.sumerge.springtask.controller;

import com.sumerge.springtask.exceptions.AuthorAlreadyExistsException;
import com.sumerge.springtask.exceptions.CourseAlreadyExistsException;
import com.sumerge.springtask.exceptions.ErrorResponse;
import com.sumerge.springtask.exceptions.NoCoursesAvailableException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<String> courseAlreadyExistsException(CourseAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse.getMessage());
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ResponseEntity<String> authorAlreadyExistsException(AuthorAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse.getMessage());
    }

    @ExceptionHandler(NoCoursesAvailableException.class)
    public ResponseEntity<String> noCoursesAvailableException(NoCoursesAvailableException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
