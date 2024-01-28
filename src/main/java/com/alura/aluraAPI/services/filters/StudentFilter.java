package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.person.read.AccountBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.AccountStudentDTO;
import com.alura.aluraAPI.dtos.person.read.AccountUnBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.services.filters.validation.student.StudentBlockNameStrategy;
import com.alura.aluraAPI.services.filters.validation.student.StudentNameStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StudentFilter {
    private StudentBlockNameStrategy studentBlockNameStrategy;
    private StudentNameStrategy studentNameStrategy;

    public StudentFilter(StudentBlockNameStrategy studentBlockNameStrategy, StudentNameStrategy studentNameStrategy) {
        this.studentBlockNameStrategy = studentBlockNameStrategy;
        this.studentNameStrategy = studentNameStrategy;
    }

    public List<AccountStudentDTO> filter(SearchStudentDTO studentDTO, String typeStudent) {
        if (typeStudent.equalsIgnoreCase("block")){
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
