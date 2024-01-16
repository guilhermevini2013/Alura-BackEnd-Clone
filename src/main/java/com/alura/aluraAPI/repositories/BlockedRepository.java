package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedRepository extends JpaRepository<Blocked,Long> {
    void deleteByIdStudentBlocked(Student student);
}
