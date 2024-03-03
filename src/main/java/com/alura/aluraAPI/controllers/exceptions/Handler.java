package com.alura.aluraAPI.controllers.exceptions;

import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class Handler {
    private Integer status;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> notFound(ResourceNotFoundException e, HttpServletRequest request) {
        status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(), status, List.of(e.getMessage()), request.getServletPath()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ErrorModel> dataBase(DataBaseException e, HttpServletRequest request) {
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(), status, List.of(e.getMessage()), request.getServletPath()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorModel> validationException(ValidationException e, HttpServletRequest request) {
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(), status, List.of(e.getMessage()), request.getServletPath()));
    }

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity<ErrorModel> credentialsIncorrect(HttpServletRequest request) {
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(), status, List.of("Email or Password incorrect"), request.getContextPath()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> validationBean(MethodArgumentNotValidException e, HttpServletRequest request) {
        status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(new ErrorModel(Instant.now(), status, e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList(), request.getContextPath()));
    }
}
