package com.alura.aluraAPI.dtos.person.insert;

import com.alura.aluraAPI.models.person.TypeSignature;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentInsertDTO(@NotBlank(message = "Required field")
                               String name,
                               @Email(message = "Email incorrect")
                               @NotBlank(message = "Required field")
                               String email,
                               @NotBlank(message = "Required field")
                               @Size(min = 6, message = "minimum of 6 characters")
                               String password,
                               @NotBlank(message = "Required field")
                               TypeSignature typeSignature) {
}
