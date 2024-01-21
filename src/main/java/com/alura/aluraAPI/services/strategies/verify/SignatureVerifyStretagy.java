package com.alura.aluraAPI.services.strategies.verify;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class SignatureVerifyStretagy implements IVerify {

    @Override
    public void verify(Student student) {
        Date finalDate = student.getSignature().getFinalDate();
        Date nowDate = Date.from(Instant.now());
        if (nowDate.compareTo(finalDate) > 0) {
            throw new ValidationException("Signature expired: " + finalDate);
        }
    }
}
