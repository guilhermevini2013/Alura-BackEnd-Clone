package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.insert.VideoLessonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public Course(CourseDTO cursesDTO) {
        super(cursesDTO.nameContent(), cursesDTO.description());
        super.totalHours = calculateTotalHours(cursesDTO.videoLessonDTOList());
        super.certificate = new Certificate(cursesDTO.certificateDTO(), this);
        cursesDTO.videoLessonDTOList().stream().forEach(x -> videoLessons.add(new VideoLesson(x, this)));
    }

    private Integer calculateTotalHours(List<VideoLessonDTO> videoList) {
        return videoList.stream().mapToInt(x -> x.duration()).sum() / 60;
    }

    public Course setTrainings(Training trainings) {
        this.trainings = trainings;
        return this;
    }
}
