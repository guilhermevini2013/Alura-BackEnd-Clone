package com.alura.aluraAPI.controllers.content;

import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.ContentSearchDTO;
import com.alura.aluraAPI.services.contents.CurseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private CurseService courseService;

    public CourseController(CurseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody @Valid CourseDTO courseDTO) {
        courseDTO = courseService.insert(courseDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(courseDTO).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ContentReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public ResponseEntity<Page<ContentReadDTO>> findAllCurse(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                             @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                             @RequestParam(name = "orderBy", defaultValue = "publicationDate") String orderBy) {
        return ResponseEntity.ok(courseService.findAllCurse(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public ResponseEntity<List<ContentReadDTO>> findAllCourse(@RequestParam(name = "course", required = false) String nameCourse,
                                                              @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                              @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(courseService.findByFilter(new ContentSearchDTO(nameCourse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteByName(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
