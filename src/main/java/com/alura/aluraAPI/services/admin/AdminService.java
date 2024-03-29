package com.alura.aluraAPI.services.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.read.*;
import com.alura.aluraAPI.models.person.Admin;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import com.alura.aluraAPI.repositories.AdminRepository;
import com.alura.aluraAPI.repositories.BlockedRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.email.EmailService;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.filters.StudentFilter;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeBlockedStrategy;
import com.alura.aluraAPI.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final BlockedRepository blockedRepository;
    private final CalculateTimeBlockedStrategy calculateTimeBlockedStrategy;
    private final DashBoardComponent dashBoardComponent;
    private final StudentFilter studentFilter;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final EmailService emailService;

    @Transactional
    public void blockAccount(Long idAccount, Integer time) {
        Student entity = studentRepository.findById(idAccount).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        entity.setIsAccountNonLocked(false);
        Blocked blockedSaved = blockedRepository.save(new Blocked(entity, time, calculateTimeBlockedStrategy));
        emailService.sendEmailToStudent(entity.getEmail(), "Conta bloqueada", "Ola " + entity.getName() + " sua conta foi bloqueada ate a data de: " + blockedSaved.getExpirationDate());

    }

    @Transactional
    public void unBlockAccount(Long idAccount) {
        Student entity = studentRepository.findById(idAccount).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        entity.setIsAccountNonLocked(true);
        blockedRepository.deleteByIdStudentBlocked(entity);
        emailService.sendEmailToStudent(entity.getEmail(), "Conta bloqueada", "Ola " + entity.getName() + " sua conta foi desbloqueada");
    }

    @Transactional(readOnly = true)
    public Page<AccountBlockedDTO> findAllAccountBlocked(PageRequest request) {
        return blockedRepository.findAll(request).map(entity -> new AccountBlockedDTO(entity));
    }

    @Transactional(readOnly = true)
    public Page<AccountUnBlockedDTO> findAllAccountUnBlocked(PageRequest request) {
        return studentRepository.findAllStudentNotBlocked(request).map(account -> new AccountUnBlockedDTO(account));
    }

    public List<AccountStudentDTO> findStudentByFilter(SearchStudentDTO studentDTO, String typeStudent) {
        return studentFilter.filter(studentDTO, typeStudent);
    }

    public DashBoardReadDTO getDashboard() {
        return dashBoardComponent.getValues();
    }

    public LoginToken loginAdmin(PersonLoadDTO personLoadDTO) {
        adminRepository.findByEmail(personLoadDTO.email()).orElseThrow(() -> new ResourceNotFoundException("Email incorrect or no exists"));
        var usernamePassword = new UsernamePasswordAuthenticationToken(personLoadDTO.email(), personLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Admin) auth.getPrincipal());
        return new LoginToken(token);
    }
}
