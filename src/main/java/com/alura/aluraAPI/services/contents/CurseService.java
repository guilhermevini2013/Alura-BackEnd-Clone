package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.CurseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public CurseReadDTO findByName(String name){
        Curse entityFind = contentRepository.findByNameContent(name).orElseThrow(()-> new RuntimeException());
        return new CurseReadDTO(entityFind);
    }
    @Transactional(readOnly = true)
    public Page<CurseReadDTO> findAllCurse(PageRequest pageRequest){
        return contentRepository.findAllCurse(pageRequest).map(x->new CurseReadDTO(x));
    }
}
