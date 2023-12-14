package com.alura.aluraAPI.dtos.content;

import com.alura.aluraAPI.models.content.Certificate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;

public record CertificateDTO(@JsonIgnore Long id,
                             @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameCertificate,
                             Integer duration) {
    public CertificateDTO(Certificate certificate) {
        this(certificate.getId(), certificate.getNameCertificate(), certificate.getTotalHours());
    }
}
