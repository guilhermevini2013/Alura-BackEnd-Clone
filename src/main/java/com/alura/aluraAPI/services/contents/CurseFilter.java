package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseSearchDTO;
import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.FilterSpecification;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CurseFilter {
    private final FilterSpecification<Content> filterSpecification = new FilterSpecification<>();
    private ContentRepository contentRepository;

    public CurseFilter(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<CurseReadDTO> filter(CurseSearchDTO dto){
        Set<CurseReadDTO> listFilter = new HashSet<>();
        if (dto.nameContent()!=null) {
            contentRepository.findAll(filterSpecification.filterByString("nameContent", dto.nameContent()))
                    .forEach(x-> listFilter.add(new CurseReadDTO((Curse) x)));
        }
        if (dto.assessment() != null){
            contentRepository.findAll(filterSpecification.filterByDouble("assessment", dto.assessment()))
                    .forEach(x-> listFilter.add(new CurseReadDTO((Curse) x)));
        }
        if (dto.publicationDate() != null){
            contentRepository.findAll(filterSpecification.filterByLocalDate("publicationDate", dto.publicationDate()))
                    .forEach(x-> listFilter.add(new CurseReadDTO((Curse) x)));
        }
        return listFilter.stream().toList();
    }

}
