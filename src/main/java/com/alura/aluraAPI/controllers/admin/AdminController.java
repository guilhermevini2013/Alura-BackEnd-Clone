package com.alura.aluraAPI.controllers.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.read.*;
import com.alura.aluraAPI.services.admin.AdminService;
import com.alura.aluraAPI.services.email.EmailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private AdminService adminService;
    private EmailService emailService;

    public AdminController(AdminService adminService, EmailService emailService) {
        this.adminService = adminService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginToken> loginAdmin(@RequestBody PersonLoadDTO personLoadDTO) {
        return ResponseEntity.ok(adminService.loginAdmin(personLoadDTO));
    }

    @PostMapping(value = "/block/{id}")
    public ResponseEntity<String> blockAccount(@PathVariable Long id, @RequestParam(name = "timeInHours") Integer timeInHours) {
        adminService.blockAccount(id, timeInHours);
        return ResponseEntity.ok("Id " + id + " successfully Blocked");
    }

    @PostMapping(value = "/unblock/{id}")
    public ResponseEntity<String> unBlockAccount(@PathVariable Long id) {
        adminService.unBlockAccount(id);
        return ResponseEntity.ok("Id " + id + " successfully Unblocked");
    }

    @GetMapping(value = "/blocked")
    public ResponseEntity<Page<AccountBlockedDTO>> findAllAccountBlocked(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                                         @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                                         @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id", required = false) String orderBy) {
        return ResponseEntity.ok(adminService.findAllAccountBlocked(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/dashboard")
    public ResponseEntity<DashBoardReadDTO> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboard());
    }

    @GetMapping(value = "/unblocked")
    public ResponseEntity<Page<AccountUnBlockedDTO>> findAllStudentUnblock(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                                           @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                                           @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                           @RequestParam(name = "orderBy", defaultValue = "id", required = false) String orderBy) {
        return ResponseEntity.ok(adminService.findAllAccountUnBlocked(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/student/filter")
    public ResponseEntity<List<AccountStudentDTO>> findByFilter(@RequestParam(name = "id", required = false) Long id,
                                                                @RequestParam(name = "name", required = false) String name,
                                                                @RequestParam(name = "typeStudent") String typeStudent) {
        return ResponseEntity.ok(adminService.findUnblockByFilter(new SearchStudentDTO(name, id), typeStudent));
    }
}
