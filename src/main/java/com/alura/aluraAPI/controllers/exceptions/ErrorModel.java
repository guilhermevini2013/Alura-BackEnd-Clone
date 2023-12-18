package com.alura.aluraAPI.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorModel {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
