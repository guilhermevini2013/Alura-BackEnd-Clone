package com.alura.aluraAPI.dtos.forum.insert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record PublicationDto(@NotBlank String title,
                             @NotBlank String description,
                             @NotNull @Size(min = 1) Set<Long> ids_categories,
                             @NotNull Long id_student) {
}