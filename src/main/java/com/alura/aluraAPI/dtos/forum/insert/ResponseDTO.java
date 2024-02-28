package com.alura.aluraAPI.dtos.forum.insert;

import com.alura.aluraAPI.models.forum.Response;
import jakarta.validation.constraints.NotBlank;

public record ResponseDTO(@NotBlank(message = "Required field") String content,
                          @NotBlank(message = "Required field") Long idStudent) {
    public ResponseDTO(Response entityResponse) {
        this(entityResponse.getContent(), entityResponse.getId());
    }
}
