package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.services.calculates.CalculateTimeCourseStrategy;
import com.alura.aluraAPI.services.calculates.ICalculable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Course")
public class Course extends Content {
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<VideoLesson> videoLessons = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Training trainings;
    @Transient
    private ICalculable<Course> calculable;

    public Course(CourseDTO cursesDTO, CalculateTimeCourseStrategy timeCourse) {
        super(cursesDTO.nameContent(), cursesDTO.description());
        super.certificate = new Certificate(cursesDTO.certificateDTO(), this);
        cursesDTO.videoLessonDTOList().stream().forEach(x -> videoLessons.add(new VideoLesson(x, this)));
        this.calculable = timeCourse;
        super.totalHours = calculable.calculateTime(this);
    }

    public List<VideoLesson> getVideoLessons() {
        return Collections.unmodifiableList(videoLessons);
    }

    public Course setTrainings(Training trainings) {
        this.trainings = trainings;
        return this;
    }
}
