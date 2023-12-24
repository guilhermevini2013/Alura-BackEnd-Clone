package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.models.content.Training;
import com.alura.aluraAPI.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingService {
    private ContentRepository contentRepository;

    public TrainingService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }
    @Transactional
    public TrainingReadDTO insert(TrainingInsertDTO trainingInsertDTO){
        Training entity = contentRepository.save(new Training(trainingInsertDTO));
        return new TrainingReadDTO(entity);
    }
}
