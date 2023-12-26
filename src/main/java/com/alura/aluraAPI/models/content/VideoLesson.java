package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.VideoLessonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video_lesson")
public class VideoLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameLesson;
    private String archive;
    private Integer duration;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public VideoLesson(VideoLessonDTO videoLessonDTO, Course curse) {
        this.nameLesson = videoLessonDTO.nameLesson();
        this.archive = videoLessonDTO.archive();
        this.course = curse;
        this.duration = videoLessonDTO.duration();
    }
}
