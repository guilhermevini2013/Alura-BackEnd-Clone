package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Curse;

public record CurseReadDTO(String nameContent,
                           String description) {
    public CurseReadDTO(Curse curse) {
        this(curse.getNameContent(), curse.getDescription());
    }
}
