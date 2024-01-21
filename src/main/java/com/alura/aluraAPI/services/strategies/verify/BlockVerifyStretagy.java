package com.alura.aluraAPI.services.strategies.verify;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import com.alura.aluraAPI.repositories.BlockedRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class BlockVerifyStretagy implements IVerify {
    private BlockedRepository blockedRepository;

    public BlockVerifyStretagy(BlockedRepository blockedRepository) {
        this.blockedRepository = blockedRepository;
    }

    @Override
    public void verify(Student student) {
        Optional<Blocked> studentBlocked = blockedRepository.findByIdStudentBlocked(student);
        if (studentBlocked.isPresent()) {
            Date expirationDate = studentBlocked.get().getExpirationDate();
            Date nowDate = Date.from(Instant.now());
            if (nowDate.compareTo(expirationDate) > 0) {
                blockedRepository.deleteByIdStudentBlocked(student);
            }
        }
    }
}
