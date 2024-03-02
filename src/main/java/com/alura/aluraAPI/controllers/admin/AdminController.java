package com.alura.aluraAPI.controllers.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.read.*;
import com.alura.aluraAPI.services.admin.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Controller for Admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping(value = "/login")
    @Operation(description = "Login admin",
            summary = "Login admin",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Validation field", responseCode = "400"),
                    @ApiResponse(description = "Email incorrect or no exists", responseCode = "404")
            }
    )
    public ResponseEntity<LoginToken> loginAdmin(@RequestBody @Valid PersonLoadDTO personLoadDTO) {
        return ResponseEntity.ok(adminService.loginAdmin(personLoadDTO));
    }

    @PutMapping(value = "/block/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Block account student",
            summary = "Block account student",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Student not found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<String> blockAccount(@PathVariable Long id, @RequestParam(name = "timeInHours") Integer timeInHours) {
        adminService.blockAccount(id, timeInHours);
        return ResponseEntity.ok("Id " + id + " successfully Blocked");
    }

    @PutMapping(value = "/unblock/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Unblock account student",
            summary = "Unblock account student",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Student not found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<String> unBlockAccount(@PathVariable Long id) {
        adminService.unBlockAccount(id);
        return ResponseEntity.ok("Id " + id + " successfully Unblocked");
    }

    @GetMapping(value = "/blocked")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Find all account blocked pagination",
            summary = "Find all account blocked pagination",
            responses = {@ApiResponse(description = "OK",
                    responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<Page<AccountBlockedDTO>> findAllAccountBlocked(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                                         @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                                         @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id", required = false) String orderBy) {
        return ResponseEntity.ok(adminService.findAllAccountBlocked(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/dashboard")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Find for information from dashboard",
            summary = "Find for information from dashboard",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<DashBoardReadDTO> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboard());
    }

    @GetMapping(value = "/unblocked")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Find all account unblocked pagination",
            summary = "Find all account unblocked pagination",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<Page<AccountUnBlockedDTO>> findAllStudentUnblock(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                                           @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                                           @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                           @RequestParam(name = "orderBy", defaultValue = "id", required = false) String orderBy) {
        return ResponseEntity.ok(adminService.findAllAccountUnBlocked(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/student/filter")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "Find all account blocked or unblocked by Filter",
            summary = "Find all account blocked or unblocked by Filter",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            }
    )
    public ResponseEntity<List<AccountStudentDTO>> findByFilter(@RequestParam(name = "id", required = false) Long id,
                                                                @RequestParam(name = "name", required = false) String name,
                                                                @RequestParam(name = "typeStudent") String typeStudent) {
        return ResponseEntity.ok(adminService.findStudentByFilter(new SearchStudentDTO(name, id), typeStudent));
    }
}
