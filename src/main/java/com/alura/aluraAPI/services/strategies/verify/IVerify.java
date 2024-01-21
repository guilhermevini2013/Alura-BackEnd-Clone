package com.alura.aluraAPI.services.strategies.verify;

import com.alura.aluraAPI.dtos.person.insert.StudentLoadDTO;
import com.alura.aluraAPI.models.person.Student;

public interface IVerify {
    void verify(Student student);
}
