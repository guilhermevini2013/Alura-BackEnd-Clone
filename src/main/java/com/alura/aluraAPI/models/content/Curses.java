package com.alura.aluraAPI.models.content;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curses")
@DiscriminatorValue("VideoLesson")
public class Curses extends Content{
    @OneToMany(mappedBy = "curse")
    private Set<VideoLesson> videoLessons = new HashSet<>();
    @ManyToOne
    private Trainings trainings;
}
