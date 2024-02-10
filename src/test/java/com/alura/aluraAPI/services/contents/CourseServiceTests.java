package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.repositories.CategoryRepository;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.factory.CategoryFactory;
import com.alura.aluraAPI.services.factory.CourseFactory;
import com.alura.aluraAPI.services.filters.ContentFilter;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeCourseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private ContentRepository contentRepository;
    @Mock
    private ContentFilter contentFilter;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CalculateTimeCourseStrategy calculateTimeCourseStrategy;
    private Long idExists;
    private Long idNotExists;
    private Long idIntegrity;
    private Long idCategoryExists;
    private ContentReadDTO contentReadDTO;
    private Course course;
    private Category category;

    @BeforeEach
    void setUp() {
        idExists = 1l;
        idNotExists = 2l;
        idIntegrity = 3l;
        idCategoryExists = 1l;
        course = CourseFactory.createValidCourse();
        category = CategoryFactory.createValidCategory();
        contentReadDTO = CourseFactory.createValidContentReadDTO();
        when(contentRepository.findByIdCourse(idExists)).thenReturn(Optional.of(course));
        when(contentRepository.existsById(idExists)).thenReturn(true);
        when(contentRepository.existsById(idNotExists)).thenReturn(false);
        doThrow(DataIntegrityViolationException.class).when(contentRepository).deleteById(idIntegrity);
        doThrow(ResourceNotFoundException.class).when(contentRepository).findByIdCourse(idNotExists);
        when(contentRepository.save(any())).thenReturn(course);
        when(categoryRepository.findById(idCategoryExists)).thenReturn(Optional.of(category));
    }

    @Test
    public void insertShouldInsertedCourseInDataBaseAndReturnCourseDTOAndCategoryRepositoryNotThrowException() {
        CourseDTO validCourseDTO = CourseFactory.createValidCourseDTO();
        CourseDTO entityDTO = courseService.insert(validCourseDTO);
        assertEquals(entityDTO.nameContent(), validCourseDTO.nameContent());
        verify(contentRepository, times(1)).save(any());
        verify(categoryRepository, times(1)).findById(idCategoryExists);
        verifyNoMoreInteractions(contentRepository);
        verifyNoMoreInteractions(categoryRepository);
        assertDoesNotThrow(() -> courseService.insert(validCourseDTO));
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionAndNotReturnCourseWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> courseService.findById(idNotExists));
        verify(contentRepository, times(1)).findByIdCourse(idNotExists);
        verifyNoMoreInteractions(contentRepository);
    }

    @Test
    public void findByIdShouldReturnCourseAndNotThrowExceptionWhenIdExists() {
        assertDoesNotThrow(() -> courseService.findById(idExists));
        contentReadDTO = courseService.findById(idExists);
        assertEquals(course.getNameContent(), contentReadDTO.getNameContent());
        verify(contentRepository, times(2)).findByIdCourse(idExists);
        verifyNoMoreInteractions(contentRepository);
    }

    @Test
    public void deleteShouldThrowDataIntegrityViolationExceptionAndNotDeleteWhenIdExistsButCourseHaveIntegrity() {
        when(contentRepository.existsById(idIntegrity)).thenReturn(true);
        assertThrows(DataBaseException.class, () -> courseService.delete(idIntegrity));
        verify(contentRepository, times(1)).existsById(idIntegrity);
        verify(contentRepository, times(1)).deleteById(idIntegrity);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionAndNotDeleteCourseWhenIdNotExists() {
        assertThrows(ResourceNotFoundException.class, () -> courseService.delete(idNotExists));
        verify(contentRepository, times(1)).existsById(idNotExists);
        verifyNoMoreInteractions(contentRepository);
    }

    @Test
    public void deleteShouldDeleteCourseAndNotThrowExceptionWhenIdExistsAndIntegrityNotViolation() {
        assertDoesNotThrow(() -> courseService.delete(idExists));
        verify(contentRepository, times(1)).existsById(idExists);
        verify(contentRepository, times(1)).deleteById(idExists);
        verifyNoMoreInteractions(contentRepository);
    }
}
