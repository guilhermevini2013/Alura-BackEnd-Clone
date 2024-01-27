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
public class StudentIdStrategy implements IValidatorFilterStudent {
    private StudentRepository studentRepository;
    private FilterSpecification<Student> studentSpecification;

    public StudentIdStrategy(StudentRepository studentRepository, FilterSpecification<Student> studentSpecification) {
        this.studentRepository = studentRepository;
        this.studentSpecification = studentSpecification;
    }

    @Override
    public void validate(SearchStudentDTO studentDTO, Set<AccountUnBlockedDTO> listFilter) {
        if (studentDTO.id() != null) {
            List<Student> entities = studentRepository.findAllStudentNotBlocked(studentDTO.id());
            entities.forEach(entity -> listFilter.add(new AccountUnBlockedDTO(entity)));
        }
    }
}
