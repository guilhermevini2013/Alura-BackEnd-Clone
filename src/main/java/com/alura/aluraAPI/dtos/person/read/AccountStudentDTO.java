package com.alura.aluraAPI.dtos.person.read;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.person.TypeSignature;
import com.alura.aluraAPI.models.warn.Blocked;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountStudentDTO {
    private Long id;
    private String name;
    private TypeSignature typeSignature;
    private Integer timeBlocked;

    public AccountStudentDTO(Student entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.typeSignature = entity.getSignature().getTypeSignature();
    }
    public AccountStudentDTO(Blocked entity) {
        this(entity.getIdStudentBlocked());
        this.timeBlocked = entity.getTimeInHours();
    }
}
