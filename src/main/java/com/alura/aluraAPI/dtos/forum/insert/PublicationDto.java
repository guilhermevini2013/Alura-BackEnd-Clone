package com.alura.aluraAPI.dtos.forum.insert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public record PublicationDto(@Schema(description = "Title publication")
                             @NotBlank(message = "Required field") String title,
                             @Schema(description = "Description publication")
                             @NotBlank(message = "Required field") String description,
                             @NotNull(message = "Required field")
                             @Schema(description = "List of Ids categories for publication")
                             @Length(min = 1) Set<Long> ids_categories,
                             @Schema(description = "ID of the student who post")
                             @NotBlank(message = "Required field") Long id_student) {
}