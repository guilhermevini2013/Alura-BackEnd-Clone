package com.alura.aluraAPI.services.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.dtos.person.read.AccountBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.AccountUnBlockedDTO;
import com.alura.aluraAPI.dtos.person.read.SearchStudentDTO;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import com.alura.aluraAPI.repositories.BlockedRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.filters.StudentFilter;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeBlockedStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private StudentRepository studentRepository;
    private BlockedRepository blockedRepository;
    private CalculateTimeBlockedStrategy calculateTimeBlockedStrategy;
    private DashBoardComponent dashBoardComponent;
    private StudentFilter studentFilter;

    public AdminService(StudentRepository studentRepository, BlockedRepository blockedRepository, CalculateTimeBlockedStrategy calculateTimeBlockedStrategy, DashBoardComponent dashBoardComponent, StudentFilter studentFilter) {
        this.studentRepository = studentRepository;
        this.blockedRepository = blockedRepository;
        this.calculateTimeBlockedStrategy = calculateTimeBlockedStrategy;
        this.dashBoardComponent = dashBoardComponent;
        this.studentFilter = studentFilter;
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

    public List<AccountUnBlockedDTO> findByFilter(SearchStudentDTO studentDTO) {
        return studentFilter.filter(studentDTO);
    }

    public DashBoardReadDTO getDashboard() {
        return dashBoardComponent.getValues();
    }
}
