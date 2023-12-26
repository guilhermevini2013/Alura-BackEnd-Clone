package com.alura.aluraAPI.services.strategy.filters.validation;

import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;

import java.util.Set;

public interface IValidatorFilterCurse {
    void validate(CourseSearchDTO dto, Set<CourseReadDTO> listFilter);
}
