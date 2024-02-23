package com.alura.aluraAPI.services.filters;

import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import com.alura.aluraAPI.services.filters.validation.IValidatorFilterPublication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PublicationFilter {
    private final Set<IValidatorFilterPublication<PublicationReadDto>> filterPublication;

    public Page<PublicationReadDto> filter(PublicationSeachDTO dto, PageRequest pageRequest) {
        Set<PublicationReadDto> setPublicationDto = new HashSet<>();
        filterPublication.forEach(filter -> filter.validate(dto, setPublicationDto, pageRequest));
        return new PageImpl<>(setPublicationDto.stream().toList(), pageRequest, setPublicationDto.size());
    }
}
