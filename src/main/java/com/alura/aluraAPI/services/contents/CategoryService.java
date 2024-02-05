package com.alura.aluraAPI.services.contents;

import com.alura.aluraAPI.dtos.content.readOnly.CategoryReadDTO;
import com.alura.aluraAPI.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryReadDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> new CategoryReadDTO(category)).toList();
    }
}
