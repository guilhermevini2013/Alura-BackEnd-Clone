package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ContentFilter {
    private final Set<IValidatorFilterContent> validatorFilterCurses;

    public List<ContentReadDTO> filter(ContentSearchDTO dto, Content content) {
        Set<ContentReadDTO> listFilter = new HashSet<>();
        validatorFilterCurses.forEach(validator -> validator.validate(dto, listFilter, content));
        return listFilter.stream().toList();
    }

}
