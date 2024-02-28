package com.alura.aluraAPI.dtos.content.insert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public record TrainingInsertDTO(
        @NotBlank(message = "Required field")
        @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameContent,
        @NotBlank(message = "Required field")
        @Size(min = 100, message = "minimum of 100 characters") String description,
        @NotNull(message = "Required field")
        @Length(min = 3) Set<Long> curses) {

}
