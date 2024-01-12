package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeCourseStrategy;
import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.filters.ContentFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurseService {
    private final ContentRepository contentRepository;
    private final ContentFilter curseFilter;
    private final CalculateTimeCourseStrategy timeCourse;

    public CurseService(ContentRepository contentRepository, ContentFilter curseFilter, CalculateTimeCourseStrategy timeCourse) {
        this.contentRepository = contentRepository;
        this.curseFilter = curseFilter;
        this.timeCourse = timeCourse;
    }

    @Transactional(readOnly = true)
    public List<ContentReadDTO> findByFilter(ContentSearchDTO searchDTO) {
        return curseFilter.filter(searchDTO, new Course());
    }

    @Transactional
    public CourseDTO insert(CourseDTO cursesDTO) {
        Course entity = contentRepository.save(new Course(cursesDTO, timeCourse));
        return new CourseDTO(entity);
    }

    @Transactional(readOnly = true)
    public ContentReadDTO findById(Long id) {
        Course entityFind = contentRepository.findByIdCourse(id).orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));
        return new ContentReadDTO(entityFind);
    }

    @Transactional(readOnly = true)
    public Page<ContentReadDTO> findAllCurse(PageRequest pageRequest) {
        return contentRepository.findAllCourse(pageRequest).map(course -> new ContentReadDTO(course));
    }

    @Transactional
    public void delete(Long id) {
        try {
            if (!contentRepository.existsById(id)) {
                throw new ResourceNotFoundException("Course Not Found");
            }
            contentRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }

}
