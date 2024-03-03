package com.alura.aluraAPI.dtos.person.insert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonLoadDTO(
        @Email(message = "Email incorrect")
        @Schema(description = "Email for person(Student or Admin)")
        @NotBlank(message = "Required field")
        String email,
        @NotBlank(message = "Required field")
        @Schema(description = "Password for person(Student or Admin)")
        String password) {
}
