package com.alura.aluraAPI.controllers.admin;

import com.alura.aluraAPI.services.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<String> blockAccount(@PathVariable Long id, @RequestParam(name = "timeInHours") Integer timeInHours) {
        adminService.blockAccount(id, timeInHours);
        return ResponseEntity.ok("Id " + id + " successfully Blocked");
    }
}