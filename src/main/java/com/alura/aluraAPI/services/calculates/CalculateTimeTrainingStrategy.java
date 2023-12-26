package com.alura.aluraAPI.services.calculates;

import com.alura.aluraAPI.models.content.Training;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CalculateTimeTrainingStrategy implements ICalculable<Training> {

    @Override
    public Integer calculateTime(Training training) {
        IntStream intStream = training.getCourses().stream()
                .mapToInt(x -> x.getVideoLessons().stream().mapToInt(y -> y.getDuration()).sum());
        return intStream.sum();
    }
}
