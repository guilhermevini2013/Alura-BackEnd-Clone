package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.person.read.AccountStudentDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.services.filters.validation.student.StudentBlockNameStrategy;
import com.alura.aluraAPI.services.filters.validation.student.StudentNameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StudentFilter {
    private final StudentBlockNameStrategy studentBlockNameStrategy;
    private final StudentNameStrategy studentNameStrategy;

    public List<AccountStudentDTO> filter(SearchStudentDTO studentDTO, String typeStudent) {
        if (typeStudent.equalsIgnoreCase("block")) {
            return filterBlock(studentDTO);
        } else if (typeStudent.equalsIgnoreCase("unblock")) {
            return filterUnblock(studentDTO);
        }
        return List.of();
    }

    public List<AccountStudentDTO> filterUnblock(SearchStudentDTO studentDTO) {
        Set<AccountStudentDTO> listFilter = new HashSet<>();
        studentNameStrategy.validate(studentDTO, listFilter);
        return listFilter.stream().toList();
    }

    public List<AccountStudentDTO> filterBlock(SearchStudentDTO studentDTO) {
        Set<AccountStudentDTO> listFilter = new HashSet<>();
        studentBlockNameStrategy.validate(studentDTO, listFilter);
        return listFilter.stream().toList();
    }
}
