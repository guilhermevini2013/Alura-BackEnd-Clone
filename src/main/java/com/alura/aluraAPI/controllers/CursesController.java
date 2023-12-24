package com.alura.aluraAPI.controllers;

import com.alura.aluraAPI.dtos.content.insert.CurseDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseReadDTO;
import com.alura.aluraAPI.dtos.content.readOnly.CurseSearchDTO;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<CurseReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(curseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CurseReadDTO>> findAllCurse(@RequestParam(name = "pages", defaultValue = "0") Integer page,
                                                           @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                           @RequestParam(name = "direction", defaultValue = "DESC") String direction,
                                                           @RequestParam(name = "orderBy", defaultValue = "publicationDate") String orderBy) {
        return ResponseEntity.ok(curseService.findAllCurse(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<CurseReadDTO>> findAllCurse(@RequestParam(name = "curse", required = false) String nameCurse,
                                                           @RequestParam(name = "publicationDate", required = false) LocalDate date,
                                                           @RequestParam(name = "assessment", required = false) Double assessment) {
        return ResponseEntity.ok(curseService.findByFilter(new CurseSearchDTO(nameCurse, date, assessment)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteByName(@PathVariable Long id) {
        curseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
