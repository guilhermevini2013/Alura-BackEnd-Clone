package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.readOnly.CategoryReadDTO;
import com.alura.aluraAPI.services.contents.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Controller for Category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(description = "Get all course categories",
            summary = "Get all course categories",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<List<CategoryReadDTO>> findAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
