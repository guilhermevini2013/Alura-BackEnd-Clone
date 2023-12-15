package com.alura.aluraAPI.services.content;

import com.alura.aluraAPI.dtos.content.CurseDTO;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurseService {
    private ContentRepository contentRepository;
    @Autowired
    public CurseService(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }
    @Transactional
    public CurseDTO insert(CurseDTO cursesDTO){
        Curse entity = contentRepository.save(new Curse(cursesDTO));
        return new CurseDTO(entity);
    }
    @Transactional(readOnly = true)
    public CurseDTO findByName(String name){
        Curse entityFind = contentRepository.findByNameContent(name).orElseThrow(()-> new RuntimeException());
        System.out.println(entityFind.getClass());
        return new CurseDTO(entityFind);
    }
}
