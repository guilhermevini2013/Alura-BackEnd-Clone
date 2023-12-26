package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("Training")
public class Training extends Content{
    @OneToMany(mappedBy = "trainings",cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public Training(TrainingInsertDTO trainingInsertDTO) {
        super(trainingInsertDTO.nameContent(), trainingInsertDTO.description());
    }
    public void calculatedTime(){
        IntStream intStream = courses.stream().mapToInt(x -> x.getVideoLessons().stream().mapToInt(y -> y.getDuration()).sum());
        super.totalHours = intStream.sum();
    }
}
