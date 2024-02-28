package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.services.contents.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping(value = "/course", produces = "application/json")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    @Operation(description = "Create new course on the platform",
            summary = "Create new course",
            responses = {
                    @ApiResponse(description = "Create", responseCode = "201"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Validation field", responseCode = "400"),
                    @ApiResponse(description = "Category Not found", responseCode = "404")
            })
    public ResponseEntity<Void> insert(@RequestBody @Valid CourseDTO courseDTO, UriComponentsBuilder componentsBuilder) {
        courseDTO = courseService.insert(courseDTO);
        URI uri = componentsBuilder.path("/course/id/{id}").buildAndExpand(courseDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/id/{id}")
    @Operation(description = "Find course by ID",
            summary = "Return one course by ID",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<ContentReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    @Operation(description = "Find all courses with pagination",
            summary = "Return all courses pagination",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Page<ContentReadDTO>> findAllCurse(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                             @RequestParam(name = "direction", defaultValue = "DESC", required = false) String direction,
                                                             @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy) {
        return ResponseEntity.ok(courseService.findAllCurse(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/filter")
    @Operation(description = "Find all courses with filter",
            summary = "Return all courses filter",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<List<ContentReadDTO>> findAllCourse(@RequestParam(name = "course", required = false) String nameCourse,
                                                              @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                              @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(courseService.findByFilter(new ContentSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Delete course, video lesson and certificate",
            summary = "Delete course by ID",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Course not found", responseCode = "404"),
                    @ApiResponse(description = "Data integrity violation", responseCode = "400")
            })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
