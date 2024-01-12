package com.alura.aluraAPI.models.warn;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeBlockedStrategy;
import com.alura.aluraAPI.services.strategies.calculates.ICalculable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "student_blocked")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
public class Blocked {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Student idStudentBlocked;
    private Integer timeInHours;
    private Date expirationDate;
    @Transient
    private ICalculable<Blocked> calculable;

    public Blocked(Student idStudentBlocked, Integer timeInHours, CalculateTimeBlockedStrategy calculateTimeBlockedStrategy) {
        this.idStudentBlocked = idStudentBlocked;
        this.timeInHours = timeInHours;
        this.calculable = calculateTimeBlockedStrategy;
        calculable.calculateTime(this);
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
