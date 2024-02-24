package com.alura.aluraAPI.dtos.forum.insert;

import com.alura.aluraAPI.models.forum.Response;

public record ResponseDTO(String content, Long idStudent) {
    public ResponseDTO(Response entityResponse) {
        this(entityResponse.getContent(), entityResponse.getId());
    }
}
