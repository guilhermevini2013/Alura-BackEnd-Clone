package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.models.content.Curse;
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
        insertCursesInTraining(entity,trainingInsertDTO);
        entity.calculatedTime();
        entity = contentRepository.save(entity);
        return new TrainingReadDTO(entity);
    }

    private void insertCursesInTraining(Training entity, TrainingInsertDTO trainingInsertDTO){
        Set<Curse> curseList = new HashSet<>();
        if (trainingInsertDTO.curses().size() < 3){
            throw new ValidationException("Not enough courses");
        }
        for (Long idCurse: trainingInsertDTO.curses()) {
            Curse curse = contentRepository.findByIdContent(idCurse).orElse(null);
            if (curse != null && curse.getTrainings() == null){
                curseList.add(curse);
            }else {
                throw new ValidationException("Course already in use or doesn't exist");
            }
        }
        curseList.forEach(curse -> entity.getCurses().add(curse.setTrainings(entity)));
    }

}
