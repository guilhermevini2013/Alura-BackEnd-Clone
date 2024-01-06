package com.alura.aluraAPI.models.person;

import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.services.calculates.CalculateTimeSignatureStrategy;
import com.alura.aluraAPI.services.calculates.ICalculable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "signature")
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeSignature typeSignature;
    private Date initialDate;
    private Date finalDate;
    @Transient
    private ICalculable<Signature> calculable;

    public Signature(TypeSignature typeSignature, CalculateTimeSignatureStrategy timeSignatureStrategy) {
        this.typeSignature = typeSignature;
        this.initialDate = Date.from(Instant.now());
        this.calculable = timeSignatureStrategy;
        calculable.calculateTime(this);
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
