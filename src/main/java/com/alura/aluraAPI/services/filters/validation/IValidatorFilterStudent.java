package com.alura.aluraAPI.services.filters.validation;

import com.alura.aluraAPI.dtos.person.read.AccountUnBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;

import java.util.Set;

public interface IValidatorFilterStudent {
    void validate(SearchStudentDTO studentDTO, Set<AccountUnBlockedDTO> listFilter);
}
