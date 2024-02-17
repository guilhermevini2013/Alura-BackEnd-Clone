package com.alura.aluraAPI.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
