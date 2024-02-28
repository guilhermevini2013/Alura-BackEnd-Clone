package com.alura.aluraAPI.dtos.forum.read;

import com.alura.aluraAPI.models.forum.Response;

public record ResponseReadDTO(Long id, String content, String nameStudent) {
    public ResponseReadDTO(Response entityResponse) {
        this(entityResponse.getId(), entityResponse.getContent(), entityResponse.getStudent().getName());
    }
}
