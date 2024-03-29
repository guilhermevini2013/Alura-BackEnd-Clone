package com.alura.aluraAPI.services.filters.validation.student;

import com.alura.aluraAPI.dtos.person.read.AccountStudentDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.models.warn.Blocked;
import com.alura.aluraAPI.repositories.BlockedRepository;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StudentBlockNameStrategy implements IValidatorFilterStudent<AccountStudentDTO> {
    private final BlockedRepository studentRepository;

    @Override
    public void validate(SearchStudentDTO studentDTO, Set<AccountStudentDTO> listFilter) {
        if (studentDTO.name() != null) {
            List<Blocked> entities = studentRepository.findAllStudentBlocked(studentDTO.name());
            entities.forEach(entity -> listFilter.add(new AccountStudentDTO(entity)));
        }
    }
}
