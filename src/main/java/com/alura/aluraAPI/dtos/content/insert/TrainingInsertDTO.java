package com.alura.aluraAPI.dtos.content.insert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public record TrainingInsertDTO(@Schema(description = "Value generated by DataBase")
                                @JsonIgnore Long id,
                                @NotBlank(message = "Required field")
                                @Schema(description = "Name Training")
                                @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameContent,
                                @NotBlank(message = "Required field")
                                @Schema(description = "Description for Training")
                                @Size(min = 100, message = "minimum of 100 characters") String description,
                                @NotNull(message = "Required field")
                                @Schema(description = "List of Ids courses")
                                @Length(min = 3) Set<Long> curses) {

}
