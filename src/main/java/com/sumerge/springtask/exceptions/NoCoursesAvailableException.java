package com.sumerge.springtask.exceptions;

public class NoCoursesAvailableException extends RuntimeException {
    public NoCoursesAvailableException(String message) {
        super(message);
    }
}
