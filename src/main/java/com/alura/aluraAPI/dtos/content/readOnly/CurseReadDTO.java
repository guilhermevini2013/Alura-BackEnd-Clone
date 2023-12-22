package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Curse;

import java.time.LocalDate;

public record CurseReadDTO(String nameContent,
                           String description,
                           LocalDate publicationDate) {
    public CurseReadDTO(Curse curse) {
        this(curse.getNameContent(), curse.getDescription(),curse.getPublicationDate());
    }
}
