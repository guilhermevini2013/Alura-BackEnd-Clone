package com.alura.aluraAPI.dtos.person.insert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonLoadDTO(
        @Email(message = "Email incorrect")
        @NotBlank(message = "Required field")
        String email,
        @NotBlank(message = "Required field")

        @Size(min = 6, message = "Minimum of 6 characters")
        String password) {
}
