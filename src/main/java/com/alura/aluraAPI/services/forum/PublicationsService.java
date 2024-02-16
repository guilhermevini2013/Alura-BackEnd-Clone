package com.alura.aluraAPI.services.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationsAlterDto;
import com.alura.aluraAPI.dtos.forum.insert.PublicationsDto;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.forum.Publications;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.CategoryRepository;
import com.alura.aluraAPI.repositories.PublicationsRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;

    public boolean insert(PublicationsDto publicationsDto) {
        Publications entity = new Publications(publicationsDto);
        insertStudentInPublication(publicationsDto.id_student(), entity);
        insertCategoriesInPublication(publicationsDto.ids_categories(), entity);
        publicationsRepository.save(entity);
        return true;
    }

    public void alterPublication(Long idPublication, PublicationsAlterDto publicationsAlterDto) {
        Publications publications = publicationsRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        publicationsRepository.save(alter(publications, publicationsAlterDto));
    }

    public Publications alter(Publications entity, PublicationsAlterDto dto) {
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setCategories(insertCategoriesInPublication(dto.ids_categories(), entity));
        return entity;
    }

    public void deleteById(Long idPublication) {
        Publications publications = publicationsRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        publications.getStudent().getPublications().removeIf(publication -> publication.getId() == idPublication);
        publicationsRepository.save(publications);
    }

    private void insertStudentInPublication(Long idStudent, Publications publications) {
        Student studentFind = studentRepository.findById(idStudent).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        publications.setStudent(studentFind);
    }

    private Set<Category> insertCategoriesInPublication(Set<Long> categoriesIds, Publications publication) {
        Set<Category> categoriesTemp = new HashSet<>();
        for (Long id : categoriesIds) {
            Category categoryFound = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            categoriesTemp.add(categoryFound);
        }
        publication.setCategories(categoriesTemp);
        return categoriesTemp;
    }
}
