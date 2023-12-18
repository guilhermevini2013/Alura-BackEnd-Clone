package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.CurseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.models.content.Curse;
import com.alura.aluraAPI.repositories.ContentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public CurseReadDTO findById(Long id){
        Curse entityFind = contentRepository.findByIdContent(id).orElseThrow(()-> new RuntimeException());
        return new CurseReadDTO(entityFind);
    }
    @Transactional(readOnly = true)
    public Page<CurseReadDTO> findAllCurse(PageRequest pageRequest){
        return contentRepository.findAllCurse(pageRequest).map(x->new CurseReadDTO(x));
    }
    @Transactional
    public void delete(Long id){
        try{
            if (contentRepository.existsById(id)){
                contentRepository.deleteById(id);
            }else {
                throw new EntityNotFoundException();
            }
        }catch (DataIntegrityViolationException | EntityNotFoundException ex){
            throw new RuntimeException();
        }
    }
}
