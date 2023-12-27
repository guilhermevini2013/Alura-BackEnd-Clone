package com.alura.aluraAPI.services.filters.validation.training;

import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Training;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class TrainingPublicationDateNotNullStrategy implements IValidatorFilterContent<TrainingSearchDTO, TrainingReadDTO> {
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public TrainingPublicationDateNotNullStrategy(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    @Override
    public void validate(TrainingSearchDTO dto, Set<TrainingReadDTO> listFilter) {
        if (dto.publicationDate() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByLocalDate("publicationDate", dto.publicationDate()));
            for (Content content:listContent) {
                if (content instanceof Training){
                    listFilter.add(new TrainingReadDTO((Training) content));
                }
            }
        }
    }
}
