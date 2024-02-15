package com.alura.aluraAPI.dtos.forum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.Set;

public record PublicationsDto(@NotBlank String title,
                              @NotBlank String description,
                              @NotNull @Size(min = 1) Set<Long> ids_categories,
                              @NotNull Long id_student) {
}