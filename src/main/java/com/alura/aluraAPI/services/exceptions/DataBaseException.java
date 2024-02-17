package com.alura.aluraAPI.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataBaseException extends RuntimeException {
    public DataBaseException(String message) {
        super(message);
    }
}
