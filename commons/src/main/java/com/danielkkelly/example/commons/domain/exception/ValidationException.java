package com.danielkkelly.example.commons.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {
    public ValidationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
