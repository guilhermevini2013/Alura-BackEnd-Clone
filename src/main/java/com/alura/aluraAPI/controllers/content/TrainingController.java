package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.insert.TrainingInsertDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.services.contents.TrainingService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/training", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Training", description = "Controller for Training")
@SecurityRequirement(name = "bearerAuth")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    @Operation(description = "Create new training",
            summary = "Create new training",
            responses = {
                    @ApiResponse(description = "Create", responseCode = "201"),
                    @ApiResponse(description = "Validation course", responseCode = "400"),
                    @ApiResponse(description = "Validation field", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Void> insert(@RequestBody @Valid TrainingInsertDTO trainingInsertDTO, UriComponentsBuilder componentsBuilder) {
        ContentReadDTO inserted = trainingService.insert(trainingInsertDTO);
        URI uri = componentsBuilder.path("/training/id/{id}").buildAndExpand(inserted.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @Operation(description = "Find all training paginated",
            summary = "Find all training",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Page<ContentReadDTO>> findAllTraining(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "linesPerPage", defaultValue = "15") Integer linesPerPage,
                                                                @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                @RequestParam(name = "orderBy", defaultValue = "publicationDate") String orderBy) {
        return ResponseEntity.ok(trainingService.findAllTraining(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/id/{id}")
    @Operation(description = "Find one Training by ID",
            summary = "Find one Training",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Training not found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<ContentReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.findById(id));
    }

    @GetMapping(value = "/filter")
    @Operation(description = "Find all trainings by filter",
            summary = "Find all trainings by filter",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Training not found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<List<ContentReadDTO>> findAllCourse(@RequestParam(name = "training", required = false) String nameCourse,
                                                              @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                              @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(trainingService.findByFilter(new ContentSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Delete Training by ID",
            summary = "Delete Training",
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204"),
                    @ApiResponse(description = "Training not found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Data integrity violation", responseCode = "400")
            })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        trainingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
