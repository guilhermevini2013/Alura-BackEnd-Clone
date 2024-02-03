package com.alura.aluraAPI.services.filters.validation.content;

import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ContentNameNotNullStrategy implements IValidatorFilterContent<ContentSearchDTO, ContentReadDTO> {
    private final ContentRepository contentRepository;
    private final FilterSpecification<Content> filterSpecification;

    @Override
    public void validate(ContentSearchDTO dto, Set<ContentReadDTO> listFilter, Content contentInstance) {
        if (dto.nameContent() != null) {
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByString("nameContent", dto.nameContent()));
            for (Content content : listContent) {
                if (contentInstance.getClass().isInstance(content)) {
                    listFilter.add(new ContentReadDTO(content));
                }
            }
        }
    }
}
