package com.alura.aluraAPI.services.filters.validation.course;

import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class CoursePublicationDateNotNullStrategy implements IValidatorFilterContent<CourseSearchDTO,CourseReadDTO> {
    private ContentRepository contentRepository;
    private FilterSpecification<Content> filterSpecification;

    public CoursePublicationDateNotNullStrategy(ContentRepository contentRepository, FilterSpecification<Content> filterSpecification) {
        this.contentRepository = contentRepository;
        this.filterSpecification = filterSpecification;
    }

    @Override
    public void validate(CourseSearchDTO dto, Set<CourseReadDTO> listFilter) {
        if (dto.publicationDate() != null){
            List<Content> listContent = contentRepository.findAll(filterSpecification.filterByLocalDate("publicationDate", dto.publicationDate()));
            for (Content content:listContent) {
                if (content instanceof Course){
                    listFilter.add(new CourseReadDTO((Course) content));
                }
            }
        }
    }
}
