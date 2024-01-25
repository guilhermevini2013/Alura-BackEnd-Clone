package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.filters.ContentFilter;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeCourseStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private CalculateTimeCourseStrategy calculateTimeCourseStrategy;
    private Long idExists;
    @BeforeEach
    void setUp() {
        idExists = 1l;
        when(contentRepository.existsById(idExists)).thenReturn(true);
    }
    @Test
    public void deleteShouldDeleteCourseAndNotThrowExceptionWhenIdExistsAndIntegrityNotViolation(){
        assertDoesNotThrow(()-> courseService.delete(idExists));
        verify(contentRepository,times(1)).existsById(idExists);
        verify(contentRepository,times(1)).deleteById(idExists);
    }
}
