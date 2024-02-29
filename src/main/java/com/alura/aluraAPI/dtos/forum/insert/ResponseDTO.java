package com.alura.aluraAPI.dtos.forum.insert;

import com.alura.aluraAPI.models.forum.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ResponseDTO(@Schema(description = "Content of response")
                          @NotBlank(message = "Required field") String content,
                          @Schema(description = "ID of the student who commented")
                          @NotBlank(message = "Required field") Long idStudent) {
    public ResponseDTO(Response entityResponse) {
        this(entityResponse.getContent(), entityResponse.getId());
    }
}
