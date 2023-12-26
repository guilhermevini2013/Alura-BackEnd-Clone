package com.alura.aluraAPI.services.strategy.filters;

import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.services.strategy.filters.validation.IValidatorFilterCurse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class CurseFilter {
    private Set<IValidatorFilterCurse> validatorFilterCurses;

    public CurseFilter(Set<IValidatorFilterCurse> validatorFilterCurses) {
        this.validatorFilterCurses = validatorFilterCurses;
    }

    public List<CourseReadDTO> filter(CourseSearchDTO dto){
        Set<CourseReadDTO> listFilter = new HashSet<>();
        validatorFilterCurses.forEach(validator -> validator.validate(dto,listFilter));
        return listFilter.stream().toList();
    }

}
