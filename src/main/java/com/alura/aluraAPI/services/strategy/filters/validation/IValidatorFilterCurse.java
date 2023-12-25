package com.alura.aluraAPI.services.strategy.filters.validation;

import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseSearchDTO;

import java.util.Set;

public interface IValidatorFilterCurse {
    void validate(CurseSearchDTO dto,Set<CurseReadDTO> listFilter);
}
