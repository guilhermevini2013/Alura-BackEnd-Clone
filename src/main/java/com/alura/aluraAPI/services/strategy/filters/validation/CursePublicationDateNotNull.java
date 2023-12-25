package com.alura.aluraAPI.services.strategy.filters.validation;

import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.strategy.filters.FilterSpecification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class CursePublicationDateNotNull implements IValidatorFilterCurse{
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public CursePublicationDateNotNull(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    public void validate(CurseSearchDTO dto,Set<CurseReadDTO> listFilter){
        if (dto.publicationDate() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByLocalDate("publicationDate", dto.publicationDate()));
            for (Content content:listContent) {
                if (content instanceof Curse){
                    listFilter.add(new CurseReadDTO((Curse) content));
                }
            }
        }
    }
}
