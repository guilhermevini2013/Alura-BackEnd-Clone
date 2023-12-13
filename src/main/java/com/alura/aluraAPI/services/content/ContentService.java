package com.alura.aluraAPI.services.content;

import com.alura.aluraAPI.dtos.content.TrainingInsertDTO;
import com.alura.aluraAPI.services.interfaces.ICrud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentService implements ICrud<TrainingInsertDTO> {
    @Override
    public TrainingInsertDTO insert(TrainingInsertDTO trainingInsertDTO) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public TrainingInsertDTO findByName(String name) {
        return null;
    }

    @Override
    public TrainingInsertDTO updateByName(TrainingInsertDTO trainingInsertDTO, String name) {
        return null;
    }

    @Override
    public Page<TrainingInsertDTO> paginate(Pageable pageable) {
        return null;
    }
}
