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
public class TrainingAssementNotNullStrategy implements IValidatorFilterContent<TrainingSearchDTO, TrainingReadDTO> {
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public TrainingAssementNotNullStrategy(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    @Override
    public void validate(TrainingSearchDTO dto, Set<TrainingReadDTO> listFilter) {
        if (dto.assessment() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByDouble("assessment", dto.assessment()));
            for (Content content:listContent) {
                if (content instanceof Training){
                    listFilter.add(new TrainingReadDTO((Training) content));
                }
            }
        }
    }
}
