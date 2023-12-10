package com.alura.aluraAPI.models.content;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
@DiscriminatorColumn(name = "Dtype")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nameContent;
    protected Long totalStudent;
    protected Double assessment;
    protected LocalDate publicationDate;
    protected String description;
    protected Integer totalHours;
}
