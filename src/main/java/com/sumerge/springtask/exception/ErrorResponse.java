package com.sumerge.springtask.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {
    private String message;
    private int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
