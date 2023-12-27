package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.TrainingSearchDTO;
import com.alura.aluraAPI.services.contents.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<TrainingReadDTO>> findAllTraining(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "linesPerPage",defaultValue = "15") Integer linesPerPage,
                                                                 @RequestParam(name = "direction",defaultValue = "ASC") String direction,
                                                                 @RequestParam(name = "orderBy",defaultValue = "publicationDate") String orderBy){
        return ResponseEntity.ok(trainingService.findAllTraining(PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy)));
    }
    @GetMapping(value = "/filter")
    public ResponseEntity<List<TrainingReadDTO>> findAllCourse(@RequestParam(name = "course", required = false) String nameCourse,
                                                             @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                             @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(trainingService.findByFilter(new TrainingSearchDTO(nameCourse, date, assessment)));
    }
}
