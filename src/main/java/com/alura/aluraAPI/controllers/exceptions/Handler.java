package com.alura.aluraAPI.controllers.exceptions;

import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class Handler {
    private Integer status;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> notFound(ResourceNotFoundException e, HttpServletRequest request){
    status = HttpStatus.NOT_FOUND.value();
    return ResponseEntity.status(status).body(new ErrorModel(Instant.now(),status,e.getMessage(),request.getServletPath()));
    }
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ErrorModel> notFound(DataBaseException e, HttpServletRequest request){
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(),status,e.getMessage(),request.getServletPath()));
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorModel> validationException(ValidationException e, HttpServletRequest request){
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(),status,e.getMessage(),request.getServletPath()));
    }
}
