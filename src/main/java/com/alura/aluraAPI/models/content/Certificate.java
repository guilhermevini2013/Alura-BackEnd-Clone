package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.CertificateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer totalHours;
    @OneToMany(mappedBy = "certificate")
    private Set<StudentCertificate> studentCertificates = new HashSet<>();

    public Certificate(CertificateDTO certificateDTO, Curse curse) {
        this.nameCertificate = certificateDTO.nameCertificate();
        this.totalHours = curse.getTotalHours();
    }
}
