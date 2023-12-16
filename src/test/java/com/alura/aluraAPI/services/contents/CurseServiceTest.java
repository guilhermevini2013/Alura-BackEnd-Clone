package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.factory.Factory;
import com.alura.aluraAPI.repositories.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class CurseServiceTest {
    @InjectMocks
    private CurseService curseService;
    @Mock
    private ContentRepository contentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(contentRepository);
        curseService= new CurseService(this.contentRepository);
        when(contentRepository.save(any())).thenReturn(Factory.createCurse());
    }
    @Test
    public void insertShouldInsertedEntityInDataBaseAndReturnsDTOWhenNotAttributesNullOrBlanks(){
        curseService.insert(Factory.createCurseDTO());
    }
}
