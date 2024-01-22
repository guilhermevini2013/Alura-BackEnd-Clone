package com.alura.aluraAPI.services.filters.validation.student;

import com.alura.aluraAPI.dtos.person.read.AccountUnBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterStudent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StudentNameStrategy implements IValidatorFilterStudent {
    private FilterSpecification<Student> filterSpecification;
    private StudentRepository studentRepository;

    public StudentNameStrategy(FilterSpecification<Student> filterSpecification, StudentRepository studentRepository) {
        this.filterSpecification = filterSpecification;
        this.studentRepository = studentRepository;
    }

    @Override
    public void validate(SearchStudentDTO studentDTO, Set<AccountUnBlockedDTO> listFilter) {
        if (studentDTO.name() != null) {
            List<Student> entities = studentRepository.findAllStudentNotBlocked(filterSpecification.filterByString("name", studentDTO.name()));
            entities.forEach(entity -> listFilter.add(new AccountUnBlockedDTO(entity)));
        }
    }
}
