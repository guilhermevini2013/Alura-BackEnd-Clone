package com.alura.aluraAPI.dtos.person.insert;

import com.alura.aluraAPI.models.person.TypeSignature;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentInsertDTO(@NotNull
                               @NotBlank
                               String name,
                               @Email
                               @NotNull
                               @NotBlank
                               String email,
                               @NotNull
                               @NotBlank
                               String password,
                               @NotNull
                               @NotBlank
                               TypeSignature typeSignature) {
}
