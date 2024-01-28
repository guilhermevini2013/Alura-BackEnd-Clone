package com.alura.aluraAPI.services.filters.validation.student;

import com.alura.aluraAPI.dtos.person.read.AccountStudentDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterStudent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StudentNameStrategy implements IValidatorFilterStudent<AccountStudentDTO> {
    private StudentRepository studentRepository;

    public StudentNameStrategy(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void validate(SearchStudentDTO studentDTO, Set<AccountStudentDTO> listFilter) {
        if (studentDTO.name() != null) {
            List<Student> entities = studentRepository.findAllStudentNotBlocked(studentDTO.name());
            entities.forEach(entity -> listFilter.add(new AccountStudentDTO(entity)));
        }
    }
}
