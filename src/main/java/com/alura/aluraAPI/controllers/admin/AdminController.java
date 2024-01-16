package com.alura.aluraAPI.controllers.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.dtos.person.read.AccountBlockedDTO;
import com.alura.aluraAPI.services.admin.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
                                                                         @RequestParam(name = "linesPerPage", defaultValue = "20", required = false) Integer linesPerPage,
                                                                         @RequestParam(name = "direction", defaultValue = "DESC", required = false) String direction,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id", required = false) String orderBy) {
        return ResponseEntity.ok(adminService.findAllAccountBlocked(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/dashboard")
    public ResponseEntity<DashBoardReadDTO> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboard());
    }
}
