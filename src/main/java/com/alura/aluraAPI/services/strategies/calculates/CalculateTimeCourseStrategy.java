package com.alura.aluraAPI.services.strategies.calculates;

import com.alura.aluraAPI.models.content.Course;
import org.springframework.stereotype.Component;

@Component
public class CalculateTimeCourseStrategy implements ICalculable<Course> {

    @Override
    public void calculateTime(Course course) {
        course.setTotalHours(course.getVideoLessons().stream().mapToInt(lesson -> lesson.getDuration()).sum() / 60);
    }
}
