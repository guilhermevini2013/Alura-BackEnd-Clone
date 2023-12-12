package com.alura.aluraAPI.dtos.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record TrainingInsertDTO(@JsonIgnore Long id,
                                @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameContent,
                                @Size(min = 100, message = "minimum of 500 characters")String description,
                                @NotEmpty Set<CursesDTO> cursesDTOS) {
}