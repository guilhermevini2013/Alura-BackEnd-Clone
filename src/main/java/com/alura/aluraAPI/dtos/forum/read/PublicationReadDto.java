package com.alura.aluraAPI.dtos.forum.read;

import com.alura.aluraAPI.dtos.content.readOnly.CategoryReadDTO;
import com.alura.aluraAPI.models.forum.Publication;

import java.util.Set;
import java.util.stream.Collectors;

public record PublicationReadDto(Long id,
                                 String title,
                                 Integer numberOfResponses,
                                 Set<CategoryReadDTO> categories,
                                 String nameStudent) {
    public PublicationReadDto(Publication publication) {
        this(publication.getId(),
                publication.getTitle(),
                publication.getNumberOfResponses(),
                publication.getCategories().stream().map(category -> new CategoryReadDTO(category)).collect(Collectors.toSet()),
                publication.getStudent().getName());
    }
}