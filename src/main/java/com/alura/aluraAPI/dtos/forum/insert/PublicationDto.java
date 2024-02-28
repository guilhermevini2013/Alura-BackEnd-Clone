package com.alura.aluraAPI.dtos.forum.insert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public record PublicationDto(@NotBlank(message = "Required field") String title,
                             @NotBlank(message = "Required field") String description,
                             @NotNull(message = "Required field")
                             @Length(min = 1) Set<Long> ids_categories,
                             @NotBlank(message = "Required field") Long id_student) {
}