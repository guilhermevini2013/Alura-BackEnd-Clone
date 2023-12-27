package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class CurseFilter {
    private Set<IValidatorFilterContent<CourseSearchDTO,CourseReadDTO>> validatorFilterCurses;

    public CurseFilter(Set<IValidatorFilterContent<CourseSearchDTO, CourseReadDTO>> validatorFilterCurses) {
        this.validatorFilterCurses = validatorFilterCurses;
    }

    public List<CourseReadDTO> filter(CourseSearchDTO dto){
        Set<CourseReadDTO> listFilter = new HashSet<>();
        validatorFilterCurses.forEach(validator -> validator.validate(dto,listFilter));
        return listFilter.stream().toList();
    }

}
