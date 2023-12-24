package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Training;

public record TrainingReadDTO(String nameContent,
                              String description,
                              Integer totalHours,
                              Double assessment,
                              Long totalStudent) {

    public TrainingReadDTO(Training entity) {
        this(entity.getNameContent(), entity.getDescription(), entity.getTotalHours(), entity.getAssessment(), entity.getTotalStudent());
    }
}

