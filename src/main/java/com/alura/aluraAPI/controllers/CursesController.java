package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.insert.CurseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.services.contents.CurseService;
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
    public ResponseEntity<Void> insert(@RequestBody @Valid CurseDTO curseDTO) {
        curseDTO = curseService.insert(curseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(curseDTO).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<CurseReadDTO> findByName(@PathVariable String name){
        return ResponseEntity.ok(curseService.findByName(name));
    }
}
