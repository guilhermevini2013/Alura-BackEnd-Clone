package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.CertificateDTO;
import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCertificate;
    private Date initialDate;
    private Date finalDate;
    private Integer totalHours;
    @OneToOne(fetch = FetchType.LAZY)
    private Content content;
    @OneToMany
    private Set<Student> student = new HashSet<>();

    public Certificate(CertificateDTO certificateDTO, Curse curse) {
        this.nameCertificate = certificateDTO.nameCertificate();
        this.content = curse;
        this.totalHours = curse.getTotalHours();
    }
}
