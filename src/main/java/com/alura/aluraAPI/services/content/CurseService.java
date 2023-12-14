package com.alura.aluraAPI.services.content;

import com.alura.aluraAPI.dtos.content.CursesDTO;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurseService {
    private ContentRepository contentRepository;
    @Autowired
    public CurseService(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }
    public CursesDTO insert(CursesDTO cursesDTO){
        Curse entity = contentRepository.save(new Curse(cursesDTO));
        return null;
    }
}
