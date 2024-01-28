package com.alura.aluraAPI.dtos.person.read;

import com.alura.aluraAPI.models.person.TypeSignature;
import com.alura.aluraAPI.models.warn.Blocked;

public record AccountBlockedDTO(Long id,
                                String name,
                                TypeSignature typeSignature,
                                Integer timeBlocked) {
    public AccountBlockedDTO(Blocked entitiesBlocked) {
        this(entitiesBlocked.getIdStudentBlocked().getId(), entitiesBlocked.getIdStudentBlocked().getName(), entitiesBlocked.getIdStudentBlocked().getSignature().getTypeSignature(), entitiesBlocked.getTimeInHours());
    }
}
