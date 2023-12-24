package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.services.contents.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody TrainingInsertDTO trainingInsertDTO){
        TrainingReadDTO inserted = trainingService.insert(trainingInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(inserted).toUri();
        return ResponseEntity.created(uri).build();
    }
}
