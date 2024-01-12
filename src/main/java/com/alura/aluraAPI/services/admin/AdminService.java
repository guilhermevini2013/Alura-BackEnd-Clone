package com.alura.aluraAPI.services.admin;

import com.alura.aluraAPI.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private StudentRepository studentRepository;
}
