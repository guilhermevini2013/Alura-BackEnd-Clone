package com.alura.aluraAPI.services.filters.validation;

import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

public interface IValidatorFilterPublication<T> {
    void validate(PublicationSeachDTO publicationSeachDTO, Set<T> listFilter, PageRequest request);
}
