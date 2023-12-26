package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.models.content.Training;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainingService {
    private ContentRepository contentRepository;

    public TrainingService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Transactional
    public TrainingReadDTO insert(TrainingInsertDTO trainingInsertDTO){
        Training entity = new Training(trainingInsertDTO);
        addCoursesInTraining(entity,trainingInsertDTO);
        entity.calculatedTime();
        entity = contentRepository.save(entity);
        return new TrainingReadDTO(entity);
    }

    private void addCoursesInTraining(Training entity, TrainingInsertDTO trainingInsertDTO){
        Set<Course> courseList = new HashSet<>();
        if (trainingInsertDTO.curses().size() < 3){
            throw new ValidationException("Not enough courses");
        }
        for (Long idCourse: trainingInsertDTO.curses()) {
            Course course = contentRepository.findByIdContent(idCourse).orElse(null);
            if (course != null && course.getTrainings() == null){
                courseList.add(course);
            }else {
                throw new ValidationException("Course already in use or doesn't exist");
            }
        }
        courseList.forEach(course -> entity.getCourses().add(course.setTrainings(entity)));
    }

}
