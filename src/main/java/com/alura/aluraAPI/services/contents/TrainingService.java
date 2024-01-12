package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.models.content.Training;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeTrainingStrategy;
import com.alura.aluraAPI.services.exceptions.DataBaseException;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import com.alura.aluraAPI.services.filters.ContentFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrainingService {
    private final ContentFilter trainingFilter;
    private final CalculateTimeTrainingStrategy timeTraining;
    private ContentRepository contentRepository;

    public TrainingService(ContentRepository contentRepository, CalculateTimeTrainingStrategy timeTraining, ContentFilter curseFilter) {
        this.contentRepository = contentRepository;
        this.timeTraining = timeTraining;
        this.trainingFilter = curseFilter;
    }

    @Transactional
    public ContentReadDTO insert(TrainingInsertDTO trainingInsertDTO) {
        Training entity = new Training(trainingInsertDTO, timeTraining);
        addCoursesInTraining(entity, trainingInsertDTO);
        entity.calculatedTime();
        entity = contentRepository.save(entity);
        return new ContentReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ContentReadDTO> findAllTraining(PageRequest pageRequest) {
        return contentRepository.findAllTraining(pageRequest).map(training -> new ContentReadDTO(training));
    }

    @Transactional(readOnly = true)
    public ContentReadDTO findById(Long id) {
        Training entityFound = contentRepository.findByIdTraining(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new ContentReadDTO(entityFound);
    }

    @Transactional(readOnly = true)
    public List<ContentReadDTO> findByFilter(ContentSearchDTO dto) {
        return trainingFilter.filter(dto, new Training());
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            Training entityFind = contentRepository.findByIdTraining(id).orElseThrow(() -> new ResourceNotFoundException("Training Not Found"));
            removeTrainingInCourse(entityFind);
            contentRepository.delete(entityFind);
        } catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }

    private void removeTrainingInCourse(Training training) {
        training.getCourses().forEach(course -> course.setTrainings(null));
    }

    private void addCoursesInTraining(Training entity, TrainingInsertDTO trainingInsertDTO) {
        Set<Course> courseList = new HashSet<>();
        if (trainingInsertDTO.curses().size() < 3) {
            throw new ValidationException("Not enough courses");
        }
        for (Long idCourse : trainingInsertDTO.curses()) {
            Course course = contentRepository.findByIdCourse(idCourse).orElse(null);
            if (course != null) {
                courseList.add(course);
            } else {
                throw new ValidationException("Course already in use or doesn't exist");
            }
        }
        courseList.forEach(course -> entity.getCourses().add(course.setTrainings(entity)));
    }
}
