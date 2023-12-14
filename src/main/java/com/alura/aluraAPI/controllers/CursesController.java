package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.CurseDTO;
import com.alura.aluraAPI.services.content.CurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/curse")
public class CursesController {
    @Autowired
    private CurseService curseService;
    @PostMapping
    public ResponseEntity<CurseDTO> insert(@RequestBody CurseDTO curseDTO){
        curseDTO = curseService.insert(curseDTO);
        return ResponseEntity.ok(curseDTO);
    }
}
