package com.alura.aluraAPI.services.filters.validation.publication;

import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import com.alura.aluraAPI.models.forum.Publication;
import com.alura.aluraAPI.repositories.PublicationsRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterPublication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PublicationNameNotNullStrategy implements IValidatorFilterPublication<PublicationReadDto> {

    private final FilterSpecification<Publication> filterSpecification;
    private final PublicationsRepository publicationsRepository;

    @Override
    public void validate(PublicationSeachDTO publicationSeachDTO, Set<PublicationReadDto> listFilter, PageRequest pageRequest) {
        if (publicationSeachDTO.title() != null) {
            Page<Publication> listResponse = publicationsRepository.findAll(filterSpecification.filterByString("title", publicationSeachDTO.title()),pageRequest);
            listResponse.forEach(response -> {
                listFilter.add(new PublicationReadDto(response));
            });
        }
    }
}
