package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.readOnly.CategoryReadDTO;
import com.alura.aluraAPI.services.contents.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryReadDTO>> findAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
