package com.alura.aluraAPI.services.filters.validation;

import com.alura.aluraAPI.models.content.Content;

import java.util.Set;

public interface IValidatorFilterContent<S, T> {
    void validate(S dto, Set<T> listFilter, Content content);
}
