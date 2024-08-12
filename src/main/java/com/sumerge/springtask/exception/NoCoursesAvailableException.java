package com.sumerge.springtask.exception;

public class NoCoursesAvailableException extends RuntimeException {
    public NoCoursesAvailableException(String message) {
        super(message);
    }
}
