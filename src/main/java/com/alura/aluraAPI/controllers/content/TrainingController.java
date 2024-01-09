package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.services.contents.TrainingService;
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

    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody TrainingInsertDTO trainingInsertDTO) {
        ContentReadDTO inserted = trainingService.insert(trainingInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(inserted).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<ContentReadDTO>> findAllTraining(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "linesPerPage", defaultValue = "15") Integer linesPerPage,
                                                                @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                @RequestParam(name = "orderBy", defaultValue = "publicationDate") String orderBy) {
        return ResponseEntity.ok(trainingService.findAllTraining(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ContentReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.findById(id));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<ContentReadDTO>> findAllCourse(@RequestParam(name = "training", required = false) String nameCourse,
                                                              @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                              @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(trainingService.findByFilter(new ContentSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        trainingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
