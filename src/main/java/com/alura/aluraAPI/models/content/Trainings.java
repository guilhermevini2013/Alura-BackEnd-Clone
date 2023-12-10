package com.alura.aluraAPI.models.content;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("Training")
public class Trainings extends Content{
    @OneToMany(mappedBy = "trainings")
    private Set<Curses> curses = new HashSet<>();
}
