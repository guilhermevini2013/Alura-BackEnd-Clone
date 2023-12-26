package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.dtos.content.insert.CertificateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Certificate(CertificateDTO certificateDTO, Course curse) {
        this.nameCertificate = certificateDTO.nameCertificate();
        this.totalHours = curse.getTotalHours();
    }
}
