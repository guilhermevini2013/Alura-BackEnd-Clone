package com.alura.aluraAPI.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorModel {
    private Instant timestamp;
    private Integer status;
    private List<String> error;
    private String path;
}
