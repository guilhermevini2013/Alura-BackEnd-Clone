package com.alura.aluraAPI.dtos.content.insert;

import com.alura.aluraAPI.models.content.Certificate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CertificateDTO(@JsonIgnore Long id,
                             @NotBlank(message = "Required field")
                             @Size(max = 100, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameCertificate) {
    public CertificateDTO(Certificate certificate) {
        this(certificate.getId(), certificate.getNameCertificate());
    }
}
