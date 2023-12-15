package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.CurseDTO;
import com.alura.aluraAPI.services.content.CurseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/curse")
public class CursesController {
    @Autowired
    private CurseService curseService;

    @PostMapping
    public ResponseEntity<CurseDTO> insert(@RequestBody @Valid CurseDTO curseDTO) {
        curseDTO = curseService.insert(curseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(curseDTO).toUri();
        return ResponseEntity.created(uri).body(curseDTO);
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<CurseDTO> findByName(@PathVariable String name){
        return ResponseEntity.ok(curseService.findByName(name));
    }
}
