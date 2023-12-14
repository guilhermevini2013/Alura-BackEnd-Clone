package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.CurseDTO;
import com.alura.aluraAPI.dtos.content.VideoLessonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curses")
@DiscriminatorValue("VideoLesson")
public class Curse extends Content {
    @OneToMany(mappedBy = "curse", cascade = CascadeType.ALL)
    private Set<VideoLesson> videoLessons = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Training trainings;

    public Curse(CurseDTO cursesDTO) {
        super(cursesDTO.nameContent(), cursesDTO.description());
        super.certificate = new Certificate(cursesDTO.certificateDTO(),this);
        super.totalHours = calculateTotalHours(cursesDTO.videoLessonDTOList());
        cursesDTO.videoLessonDTOList().stream().forEach(x-> videoLessons.add(new VideoLesson(x,this)));
    }

    private Integer calculateTotalHours(List<VideoLessonDTO> videoList) {
        return videoList.stream().mapToInt(x -> x.duration()).sum();
    }
}
