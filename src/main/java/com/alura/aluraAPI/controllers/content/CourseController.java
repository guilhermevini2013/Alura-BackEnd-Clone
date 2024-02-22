package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.services.contents.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CourseDTO courseDTO, UriComponentsBuilder componentsBuilder) {
        courseDTO = courseService.insert(courseDTO);
        URI uri = componentsBuilder.path("/course/id/{id}").buildAndExpand(courseDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ContentReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ContentReadDTO>> findAllCurse(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPage,
                                                             @RequestParam(name = "direction", defaultValue = "DESC", required = false) String direction,
                                                             @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy) {
        return ResponseEntity.ok(courseService.findAllCurse(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<ContentReadDTO>> findAllCourse(@RequestParam(name = "course", required = false) String nameCourse,
                                                              @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                              @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(courseService.findByFilter(new ContentSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteByName(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
