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
public class CourseNameNotNull implements IValidatorFilterCurse{
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public CourseNameNotNull(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    public void validate(CourseSearchDTO dto, Set<CourseReadDTO> listFilter){
        if (dto.nameContent()!=null) {
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByString("nameContent", dto.nameContent()));
            for (Content content:listContent) {
                if (content instanceof Course){
                    listFilter.add(new CourseReadDTO((Course) content));
                }
            }
        }
    }
}
