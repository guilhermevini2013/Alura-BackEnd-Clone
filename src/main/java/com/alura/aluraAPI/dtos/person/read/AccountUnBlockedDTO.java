package com.alura.aluraAPI.dtos.person.read;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.person.TypeSignature;

public record AccountUnBlockedDTO(Long id,
                                  String name,
                                  TypeSignature typeSignature) {
    public AccountUnBlockedDTO(Student entity) {
        this(entity.getId(), entity.getName(), entity.getSignature().getTypeSignature());
    }
}
