package com.alura.aluraAPI.services.strategies.calculates;

import com.alura.aluraAPI.models.content.Training;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CalculateTimeTrainingStrategy implements ICalculable<Training> {

    @Override
    public void calculateTime(Training training) {
        IntStream intStream = training.getCourses().stream()
                .mapToInt(x -> x.getVideoLessons().stream().mapToInt(y -> y.getDuration()).sum());
        training.setTotalHours(intStream.sum());
    }
}
