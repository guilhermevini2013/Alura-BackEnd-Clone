package com.alura.aluraAPI.services.forum;

import com.alura.aluraAPI.dtos.forum.PublicationsDto;
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

    private void insertStudentInPublication(Long idStudent, Publications publications) {
        Student studentFind = studentRepository.findById(idStudent).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        publications.setStudent(studentFind);
    }

    private void insertCategoriesInPublication(Set<Long> categoriesIds, Publications publication) {
        Set<Category> categoriesTemp = new HashSet<>();
        for (Long id : categoriesIds) {
            Category categoryFound = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            categoriesTemp.add(categoryFound);
        }
        publication.setCategories(categoriesTemp);
    }
}
