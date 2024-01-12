package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeTrainingStrategy;
import com.alura.aluraAPI.services.strategies.calculates.ICalculable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("Training")
public class Training extends Content {
    @OneToMany(mappedBy = "trainings", cascade = CascadeType.PERSIST)
    private Set<Course> courses = new HashSet<>();
    @Transient
    private ICalculable<Training> calculable;

    public Training(TrainingInsertDTO trainingInsertDTO, CalculateTimeTrainingStrategy timeTraining) {
        super(trainingInsertDTO.nameContent(), trainingInsertDTO.description());
        this.calculable = timeTraining;
    }

    public void calculatedTime() {
        calculable.calculateTime(this);
    }
}
