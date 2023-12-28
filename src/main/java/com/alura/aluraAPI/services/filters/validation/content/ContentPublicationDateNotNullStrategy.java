package com.alura.aluraAPI.services.filters.validation.content;

import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class ContentPublicationDateNotNullStrategy implements IValidatorFilterContent<ContentSearchDTO,ContentReadDTO> {
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public ContentPublicationDateNotNullStrategy(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    @Override
    public void validate(ContentSearchDTO dto, Set<ContentReadDTO> listFilter, Content contentInstance) {
        if (dto.publicationDate() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByLocalDate("publicationDate", dto.publicationDate()));
            for (Content content:listContent) {
                if (contentInstance.getClass().isInstance(content)){
                    listFilter.add(new ContentReadDTO(content));
                }
            }
        }
    }
}
