package com.alura.aluraAPI.services.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.dtos.forum.insert.PublicationsAlterDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.forum.Publication;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.CategoryRepository;
import com.alura.aluraAPI.repositories.PublicationsRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.filters.PublicationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final PublicationFilter publicationFilter;

    @Transactional
    public PublicationReadDto insert(PublicationDto publicationsDto) {
        Publication entity = new Publication(publicationsDto);
        insertStudentInPublication(publicationsDto.id_student(), entity);
        insertCategoriesInPublication(publicationsDto.ids_categories(), entity);
        entity = publicationsRepository.save(entity);
        return new PublicationReadDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<PublicationReadDto> findAllByFilter(PageRequest pageRequest, PublicationSeachDTO dto) {
        return publicationFilter.filter(dto, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<PublicationReadDto> findAll(PageRequest request) {
        return publicationsRepository.findAll(request).map(PublicationReadDto::new);
    }

    @Transactional
    public void markAsResolved(Long idPublication) {
        Publication entity = publicationsRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
        entity.solvedPublish();
    }

    @Transactional
    public void alterPublication(Long idPublication, PublicationsAlterDto publicationsAlterDto) {
        Publication entity = publicationsRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
        alterInformation(entity, publicationsAlterDto);
    }

    public Publication alterInformation(Publication entity, PublicationsAlterDto dto) {
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setCategories(insertCategoriesInPublication(dto.ids_categories(), entity));
        return entity;
    }

    @Transactional
    public void deleteById(Long idPublication) {
        Publication publication = publicationsRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Publication not found"));
        publicationsRepository.delete(publication);
    }

    private void insertStudentInPublication(Long idStudent, Publication publications) {
        Student studentFind = studentRepository.findById(idStudent).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        publications.setStudent(studentFind);
    }

    private Set<Category> insertCategoriesInPublication(Set<Long> categoriesIds, Publication publication) {
        Set<Category> categoriesTemp = new HashSet<>();
        for (Long id : categoriesIds) {
            Category categoryFound = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            categoriesTemp.add(categoryFound);
        }
        publication.setCategories(categoriesTemp);
        return categoriesTemp;
    }
}
