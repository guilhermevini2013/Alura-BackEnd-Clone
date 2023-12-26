package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CourseSearchDTO;
import com.alura.aluraAPI.services.contents.CurseService;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CurseService courseService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CourseDTO courseDTO) {
        courseDTO = courseService.insert(courseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(courseDTO).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseReadDTO>> findAllCurse(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                            @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                            @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                            @RequestParam(name = "orderBy", defaultValue = "publicationDate") String orderBy) {
        return ResponseEntity.ok(courseService.findAllCurse(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<CourseReadDTO>> findAllCourse(@RequestParam(name = "course", required = false) String nameCourse,
                                                            @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                            @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(courseService.findByFilter(new CourseSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteByName(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
