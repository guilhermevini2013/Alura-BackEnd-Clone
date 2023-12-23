package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseSearchDTO;
import com.alura.aluraAPI.services.filters.validations.IValidatorFilterCurse;
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

    public List<CurseReadDTO> filter(CurseSearchDTO dto){
        Set<CurseReadDTO> listFilter = new HashSet<>();
        validatorFilterCurses.forEach(validator -> validator.validate(dto,listFilter));
        return listFilter.stream().toList();
    }

}
