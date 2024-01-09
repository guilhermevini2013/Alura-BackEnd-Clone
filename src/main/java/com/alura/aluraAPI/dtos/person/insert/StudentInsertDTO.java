package com.alura.aluraAPI.dtos.person.insert;

import com.alura.aluraAPI.models.person.TypeSignature;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentInsertDTO(@NotNull
                               @NotBlank
                               String name,
                               @Email
                               @NotNull
                               @NotBlank
                               String email,
                               @NotNull
                               @NotBlank
                               @Size(min = 6,message = "minimum of 6 characters")
                               String password,
                               @NotNull
                               @NotBlank
                               TypeSignature typeSignature) {
}
