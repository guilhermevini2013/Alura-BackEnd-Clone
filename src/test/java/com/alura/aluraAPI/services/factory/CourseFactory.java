package com.alura.aluraAPI.services.factory;

import com.alura.aluraAPI.dtos.content.insert.CertificateDTO;
import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.insert.VideoLessonDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeCourseStrategy;

import java.time.LocalDate;
import java.util.List;

public class CourseFactory {
    public static ContentReadDTO createValidContentReadDTO(){
        return new ContentReadDTO("Java","Muito bom", LocalDate.now(),10,2.0,2l);
    }
    public static VideoLessonDTO createValidVideoLessonDTO(){
        return new VideoLessonDTO(1l,"java aula 1","/java",10);
    }
    public static CourseDTO createValidCourseDTO(){
        return new CourseDTO(1l,"Java","muito bom", List.of(createValidVideoLessonDTO()),new CertificateDTO(1l,"java completo"),1l);
    }
    public static Course createValidCourse(){
        return new Course(createValidCourseDTO(),new CalculateTimeCourseStrategy());
    }
}
