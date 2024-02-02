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
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.filters.StudentFilter;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeBlockedStrategy;
import com.alura.aluraAPI.services.token.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private StudentRepository studentRepository;
    private AdminRepository adminRepository;
    private BlockedRepository blockedRepository;
    private CalculateTimeBlockedStrategy calculateTimeBlockedStrategy;
    private DashBoardComponent dashBoardComponent;
    private StudentFilter studentFilter;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AdminService(StudentRepository studentRepository, AdminRepository adminRepository, BlockedRepository blockedRepository, CalculateTimeBlockedStrategy calculateTimeBlockedStrategy, DashBoardComponent dashBoardComponent, StudentFilter studentFilter, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.blockedRepository = blockedRepository;
        this.calculateTimeBlockedStrategy = calculateTimeBlockedStrategy;
        this.dashBoardComponent = dashBoardComponent;
        this.studentFilter = studentFilter;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    public void blockAccount(Long idAccount, Integer time) {
        Student entity = studentRepository.findById(idAccount).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        entity.setIsAccountNonLocked(false);
        blockedRepository.save(new Blocked(entity, time, calculateTimeBlockedStrategy));
    }

    @Transactional
    public void unBlockAccount(Long idAccount) {
        Student entity = studentRepository.findById(idAccount).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        entity.setIsAccountNonLocked(true);
        blockedRepository.deleteByIdStudentBlocked(entity);
    }

    @Transactional(readOnly = true)
    public Page<AccountBlockedDTO> findAllAccountBlocked(PageRequest request) {
        return blockedRepository.findAll(request).map(entity -> new AccountBlockedDTO(entity));
    }

    @Transactional(readOnly = true)
    public Page<AccountUnBlockedDTO> findAllAccountUnBlocked(PageRequest request) {
        return studentRepository.findAllStudentNotBlocked(request).map(account -> new AccountUnBlockedDTO(account));
    }

    public List<AccountStudentDTO> findUnblockByFilter(SearchStudentDTO studentDTO, String typeStudent) {
        return studentFilter.filter(studentDTO,typeStudent);
    }

    public DashBoardReadDTO getDashboard() {
        return dashBoardComponent.getValues();
    }
    public LoginToken loginAdmin(PersonLoadDTO personLoadDTO){
        adminRepository.findByEmail(personLoadDTO.email()).orElseThrow(()-> new ResourceNotFoundException("Email incorrect or no exists"));
        var usernamePassword = new UsernamePasswordAuthenticationToken(personLoadDTO.email(), personLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Admin) auth.getPrincipal());
        return new LoginToken(token);
    }
}
