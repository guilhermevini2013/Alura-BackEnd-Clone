package com.alura.aluraAPI.dtos.person.insert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonLoadDTO(
                             @Email
                               @NotNull
                               @NotBlank
                               String email,
                             @NotNull
                               @NotBlank
                               String password) {
}
