package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.person.read.AccountUnBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterStudent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StudentFilter {
    private Set<IValidatorFilterStudent> filterStudents;

    public StudentFilter(Set<IValidatorFilterStudent> filterStudents) {
        this.filterStudents = filterStudents;
    }

    public List<AccountUnBlockedDTO> filter(SearchStudentDTO studentDTO) {
        Set<AccountUnBlockedDTO> listFilter = new HashSet<>();
        filterStudents.forEach(filter -> filter.validate(studentDTO, listFilter));
        return listFilter.stream().toList();
    }
}
