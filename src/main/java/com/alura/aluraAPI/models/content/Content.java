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
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nameContent;
    protected Long totalStudent;
    protected Double assessment;
    protected LocalDate publicationDate;
    protected String description;
    protected Integer totalHours;
    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Certificate certificate;

    public Content(String nameContent,String description,Integer totalHours ,Certificate certificate) {
        this.nameContent = nameContent;
        this.description = description;
        this.totalStudent = 0l;
        this.publicationDate = LocalDate.now();
        this.totalHours = totalHours;
        this.certificate = certificate;
    }
}
