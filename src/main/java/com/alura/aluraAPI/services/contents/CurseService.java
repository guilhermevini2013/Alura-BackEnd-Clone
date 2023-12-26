package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.strategy.filters.CurseFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurseService {
    private ContentRepository contentRepository;
    private CurseFilter curseFilter;

    public CurseService(ContentRepository contentRepository, CurseFilter curseFilter) {
        this.contentRepository = contentRepository;
        this.curseFilter = curseFilter;
    }

    @Transactional(readOnly = true)
    public List<CourseReadDTO> findByFilter(CourseSearchDTO searchDTO){
        return curseFilter.filter(searchDTO);
    }
    @Transactional
    public CourseDTO insert(CourseDTO cursesDTO){
        Course entity = contentRepository.save(new Course(cursesDTO));
        return new CourseDTO(entity);
    }
    @Transactional(readOnly = true)
    public CourseReadDTO findById(Long id){
        Course entityFind = contentRepository.findByIdContent(id).orElseThrow(()-> new ResourceNotFoundException("Id not found: " + id));
        return new CourseReadDTO(entityFind);
    }
    @Transactional(readOnly = true)
    public Page<CourseReadDTO> findAllCurse(PageRequest pageRequest){
        return contentRepository.findAllCourse(pageRequest).map(x->new CourseReadDTO(x));
    }
    @Transactional
    public void delete(Long id){
        try{
            if (!contentRepository.existsById(id)){
                throw new ResourceNotFoundException("Id not found: " + id);
            }
            contentRepository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Integrity Violation");
        }
    }
}
