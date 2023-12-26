package com.alura.aluraAPI.services.calculates;

import com.alura.aluraAPI.models.content.Course;
import org.springframework.stereotype.Component;

@Component
public class CalculateTimeCourseStrategy implements ICalculable<Course> {

    @Override
    public Integer calculateTime(Course course) {
        return course.getVideoLessons().stream().mapToInt(lesson -> lesson.getDuration()).sum() / 60;
    }
}
