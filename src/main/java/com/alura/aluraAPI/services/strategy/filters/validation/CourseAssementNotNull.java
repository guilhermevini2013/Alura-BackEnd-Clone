package com.alura.aluraAPI.services.strategy.filters.validation;

import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.strategy.filters.FilterSpecification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class CourseAssementNotNull implements IValidatorFilterCurse{
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public CourseAssementNotNull(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    public void validate(CourseSearchDTO dto, Set<CourseReadDTO> listFilter){
        if (dto.assessment() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByDouble("assessment", dto.assessment()));
            for (Content content:listContent) {
                if (content instanceof Course){
                    listFilter.add(new CourseReadDTO((Course) content));
                }
            }
        }
    }
}